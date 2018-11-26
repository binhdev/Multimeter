package net.multimeter.iot.activity;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.multimeter.iot.R;
import net.multimeter.iot.chart.RealtimeChart;
import net.multimeter.iot.chart.components.XAxis;
import net.multimeter.iot.chart.components.YAxis;
import net.multimeter.iot.chart.data.Entry;
import net.multimeter.iot.chart.utils.Helper;
import net.multimeter.iot.chart.utils.Utils;
import net.multimeter.iot.network.producer.DataProcessor;
import net.multimeter.iot.network.producer.UdpUnicastClient;
import net.multimeter.iot.utils.Constants;
import net.multimeter.iot.views.ZeroLine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GraphActivity extends BaseActivity {

    /**
     * GUI
     */
    @BindView(R.id.chart)
    public RealtimeChart mRealtimeChart;

    @BindView(R.id.amplitude_low)
    public ZeroLine mAmplitudeLow;

    @BindView(R.id.amplitude_high)
    public ZeroLine mAmplitudeHigh;

    @BindView(R.id.timeline_low)
    public ZeroLine mTimeLineLow;

    @BindView(R.id.timeline_high)
    public ZeroLine mTimeLineHigh;

    @BindView(R.id.tv_ch1_value)
    public TextView tvValue;

    @BindView(R.id.tv_timebase)
    public TextView tvTime;

    /**
     *
     */

    XAxis mXAxis;
    YAxis mYAxis;

    private boolean isRunning = false;

    UdpUnicastClient client;
    DataProcessor dataProcessor;
    BlockingQueue<byte[]> messageQueue;

    ExecutorService mExecutorService = Executors.newFixedThreadPool(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
        mXAxis = mRealtimeChart.getXAxis();
        mYAxis = mRealtimeChart.getYAxis();

        mAmplitudeLow.setTouchUpListener(view -> {
           mYAxis.getLowLimitLine().setYoffset(view.getTop() + view.getHeight() / 2);
        });

        mAmplitudeHigh.setTouchUpListener(view -> {
            mYAxis.getHighLimitLine().setYoffset(view.getTop() + view.getHeight() / 2);
        });

        mTimeLineLow.setTouchUpListener(view -> {
            mXAxis.getLowLimitLine().setXoffset(view.getLeft() + view.getWidth()/2);
        });

        mTimeLineHigh.setTouchUpListener(view -> {
            mXAxis.getHighLimitLine().setXoffset(view.getLeft() + view.getWidth()/2);
        });

        List<Entry> entryList = new ArrayList<>();
        messageQueue = new ArrayBlockingQueue<>(Constants.QUEUE_CAPACITY);
        client = new UdpUnicastClient(Constants.SERVER_PORT, messageQueue);
        dataProcessor = new DataProcessor(messageQueue, dataPoints -> {
            entryList.clear();
            for (PointF point : dataPoints) {
                Entry entry = new Entry(point.x, value((int)point.y));
                entryList.add(entry);
            }
            mRealtimeChart.setData(entryList);
            mRealtimeChart.notifityDataChange();
        });

        mRealtimeChart.setZoomActionListener(new RealtimeChart.IZoomAction() {
            @Override
            public void scaleValue(String value) {
                tvValue.setText(Helper.formatValue(value, ""));
            }

            @Override
            public void scaleTime(String value) {
                tvTime.setText(Helper.formatTime(value, ""));
            }

        });
    }

    private void start() {
        isRunning = true;
        mExecutorService.submit(client);
        mExecutorService.submit(dataProcessor);
    }

    public void onClickHandle(View view){
        switch (view.getId()){
            case R.id.btn_run:
                start();
                break;
        }
    }

    private float value(int e){
        return Utils.convertDataToVolt(e);
    }
}
