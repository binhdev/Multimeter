package net.multimeter.iot.components;

public class XAxis extends AxisBase {
    @Override
    public void setLowLimitLine(LimitLine mLowLimitLine) {
        this.mLowLimitLine = new LimitLine(100.0f, LimitLine.Oriental.HORIZONTAL);
    }

    @Override
    public void setHighLimitLine(LimitLine mHighLimitLine) {
        this.mHighLimitLine = new LimitLine(500.0f, LimitLine.Oriental.HORIZONTAL);
    }
}
