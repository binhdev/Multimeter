package net.multimeter.iot.chart.components;

public class YAxis extends AxisBase {

    protected float mZeroPosition = 0.0f;

    public YAxis() {
        this.mLowLimitLine = new LimitLine(100.0f, LimitLine.Oriental.VERTICAL);
        this.mHighLimitLine = new LimitLine(500.0f, LimitLine.Oriental.VERTICAL);
    }

    @Override
    public void setLowLimitLine(LimitLine mLowLimitLine) {
        this.mLowLimitLine = mLowLimitLine;
    }

    @Override
    public void setHighLimitLine(LimitLine mHighLimitLine) {
        this.mHighLimitLine = mHighLimitLine;
    }

    public float getZeroPosition() {
        return mZeroPosition;
    }

    public void setZeroPosition(float mZeroPosition) {
        this.mZeroPosition = mZeroPosition;
    }


}
