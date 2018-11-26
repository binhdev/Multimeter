package net.multimeter.iot.chart.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;

public class LimitLine extends ComponentBase {

    public enum Oriental { VERTICAL, HORIZONTAL}

    private float mLineWidth = 2.0f;
    private int mLineColor = Color.rgb(237, 91,91);

    private DashPathEffect mDashPathEffect = null;

    public LimitLine(float value, Oriental oriental){
        switch (oriental){
            case VERTICAL:
                setYoffset(value);
                break;
            case HORIZONTAL:
                setXoffset(value);
                break;
            default:
                break;
        }
    }

    public void enableDashedLine(float lineLength, float spaceLength, float phase) {
        mDashPathEffect = new DashPathEffect(new float[] {
                lineLength, spaceLength
        }, phase);
    }

    /**
     * Disables the line to be drawn in dashed mode.
     */
    public void disableDashedLine() {
        mDashPathEffect = null;
    }

    public boolean isDashedLineEnabled() {
        return mDashPathEffect == null ? false : true;
    }

    /**
     * returns the DashPathEffect that is set for this LimitLine
     *
     * @return
     */
    public DashPathEffect getDashPathEffect() {
        return mDashPathEffect;
    }
}
