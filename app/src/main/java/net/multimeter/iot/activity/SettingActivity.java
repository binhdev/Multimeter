package net.multimeter.iot.activity;

import android.os.Bundle;
import android.view.View;

import net.multimeter.iot.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    public void onClickHandle(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }
}
