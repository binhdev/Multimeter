package net.multimeter.iot.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import net.multimeter.iot.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_osc)
    public ImageView btnOsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    public void onClickHandle(View view) {
        switch (view.getId()) {
            case R.id.btn_osc:
                Intent intent = new Intent(this, GraphActivity.class);
                startActivity(intent);
                break;
        }
    }
}
