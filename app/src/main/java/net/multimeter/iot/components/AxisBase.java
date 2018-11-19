package net.multimeter.iot.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;

public abstract class AxisBase extends ComponentBase {
    private int mGridColor = Color.GRAY;
    private float mGridLineWidth = 1.0f;

    private int mAxisLineColor = Color.GRAY;
    private float mAxisLineWidth = 2.0f;

    protected LimitLine mLowLimitLine;
    protected LimitLine mHighLimitLine;

    protected boolean mDrawLabels = true;

    private DashPathEffect mLimitLineDashPathEffect = null;

    public AxisBase(){

    }

    public int getGridColor() {
        return mGridColor;
    }

    public void setGridColor(int mGridColor) {
        this.mGridColor = mGridColor;
    }

    public float getGridLineWidth() {
        return mGridLineWidth;
    }

    public void setGridLineWidth(float mGridLineWidth) {
        this.mGridLineWidth = mGridLineWidth;
    }

    public int getAxisLineColor() {
        return mAxisLineColor;
    }

    public void setAxisLineColor(int mAxisLineColor) {
        this.mAxisLineColor = mAxisLineColor;
    }

    public float getAxisLineWidth() {
        return mAxisLineWidth;
    }

    public void setAxisLinWidth(float mAxisLineWidth) {
        this.mAxisLineWidth = mAxisLineWidth;
    }

    public abstract void setLowLimitLine(LimitLine mLowLimitLine);

    public LimitLine getLowLimitLine() {
        return mLowLimitLine;
    }

    public abstract void setHighLimitLine(LimitLine mHighLimitLine);

    public LimitLine getHighLimitLine() {
        return mHighLimitLine;
    }

    public void enableLimitLineDashedLine(float lineLength, float spaceLength, float phase) {
        mLimitLineDashPathEffect = new DashPathEffect(new float[]{
                lineLength, spaceLength
        }, phase);
    }

    public void setLimitLineDashedLine(DashPathEffect effect) {
        mLimitLineDashPathEffect = effect;
    }

    public void setDrawLabels(boolean mDrawLabels) {
        this.mDrawLabels = mDrawLabels;
    }

    public boolean isDrawLabelsEnabled() {
        return mDrawLabels;
    }

    public DashPathEffect getLimitLineDashedLine(){
        return mLimitLineDashPathEffect;
    }
}
