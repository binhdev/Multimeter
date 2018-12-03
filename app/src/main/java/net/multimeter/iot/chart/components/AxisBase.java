package net.multimeter.iot.chart.components;

import android.graphics.Color;
import android.graphics.DashPathEffect;

public abstract class AxisBase extends ComponentBase {

    private int mGridColor = Color.WHITE;
    private float mGridLineWidth = 1.0f;

    private int mAxisLineColor = Color.WHITE;
    private float mAxisLineWidth = 2.0f;

    protected LimitLine mLowLimitLine;
    protected LimitLine mHighLimitLine;

    protected float scale[] = {1.0f} ;
    protected int scaleIndex = 0;

    protected boolean mDrawLabels = true;

    private DashPathEffect mLimitLineDashPathEffect = null;

    public AxisBase(){
        enableLimitLineDashedLine(10f, 10f, 0);
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

    public void setScaleRange(float range[]){
        scaleIndex = 0;
        scale = range;
    }

    public void setScaleIndex(int index) {
        scaleIndex = index;
    }

    public float getScale() {
        return scale[scaleIndex];
    }

    public void up() {
        if(scaleIndex < scale.length - 1)
            scaleIndex++;
    }

    public void down() {
        if(scaleIndex > 0)
            scaleIndex--;
    }
}
