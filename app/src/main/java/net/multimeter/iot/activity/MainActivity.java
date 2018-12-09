package net.multimeter.iot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.multimeter.iot.R;
import net.multimeter.iot.adc.data.CircularBuffer;
import net.multimeter.iot.adc.producer.DataProcessor;
import net.multimeter.iot.adc.producer.UdpUnicastClient;
import net.multimeter.iot.chart.utils.Utils;
import net.multimeter.iot.utils.AppConstants;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_second_screen)
    public ImageView btnSecondScreen;

    @BindView(R.id.tv_main)
    public TextView tvMain;

    @BindView(R.id.tv_second)
    public TextView tvSecond;

    private boolean enableSecondScreen = false;

    UdpUnicastClient client;
    DataProcessor dataProcessor;
    CircularBuffer circularBuffer;

    ExecutorService mExecutorService = Executors.newFixedThreadPool(2);

    private boolean isRunning = false;
    private boolean isConnect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        circularBuffer = new CircularBuffer(AppConstants.QUEUE_BUFFER_SECOND);
        circularBuffer.setStep(100);
        client = new UdpUnicastClient(AppConstants.SERVER_PORT, circularBuffer);
        dataProcessor = new DataProcessor(circularBuffer, dataPoints -> {
            if(isRunning) {
                short rs[] = Utils.findMinMaxValue(dataPoints);
                int value = Math.abs(rs[0] - rs[1]);
                runOnUiThread(() -> tvMain.setText(String.format(Locale.US, "%.2f", Utils.convertDataToVolt(value))));
            }
        });
        dataProcessor.setTimeDelay(AppConstants.TIME_DELAY_CALCULATE);
    }

    private void start() {
        mExecutorService.submit(client);
        mExecutorService.submit(dataProcessor);
    }

    public void onClickHandle(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_osc:
                intent = new Intent(this, GraphActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_setting:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_second_screen:
                enableSecondScreen = !enableSecondScreen;
                break;
            case R.id.btn_play:
                isRunning = true;
                start();
                break;
            case R.id.btn_pause:
                isRunning = false;
        }
        updateGUI();
    }

    private void updateGUI() {
        if(enableSecondScreen){
            tvSecond.setVisibility(View.VISIBLE);
            btnSecondScreen.setImageResource(R.drawable.button_of);
        }else{
            tvSecond.setVisibility(View.GONE);
            btnSecondScreen.setImageResource(R.drawable.button_on);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(client != null)
            client.stop();
        if(dataProcessor != null)
            dataProcessor.stop();

        mExecutorService.shutdown();
    }
}
