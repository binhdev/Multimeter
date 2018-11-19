package net.multimeter.iot.components;

public class YAxis extends AxisBase {

    protected float mZeroPosition = 0.0f;

    @Override
    public void setLowLimitLine(LimitLine mLowLimitLine) {
        this.mLowLimitLine = new LimitLine(100.0f, LimitLine.Oriental.VERTICAL);
    }

    @Override
    public void setHighLimitLine(LimitLine mHighLimitLine) {
        this.mHighLimitLine = new LimitLine(500.0f, LimitLine.Oriental.VERTICAL);
    }

    public float getZeroPosition() {
        return mZeroPosition;
    }

    public void setZeroPosition(float mZeroPosition) {
        this.mZeroPosition = mZeroPosition;
    }


}
