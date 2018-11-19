package net.multimeter.iot.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WaveView extends SurfaceView implements SurfaceHolder.Callback {

    private final static String TAG = WaveView.class.getName();
    private final int TIME_UNIT = 10;
    private final int VALUE_UNIT = 8;

    private SurfaceHolder mSurfaceHolder;
    private Context mContext;


    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
