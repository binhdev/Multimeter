package net.multimeter.iot.chart.components;

public class XAxis extends AxisBase {
    public XAxis() {
        this.mLowLimitLine = new LimitLine(100.0f, LimitLine.Oriental.HORIZONTAL);
        this.mHighLimitLine = new LimitLine(500.0f, LimitLine.Oriental.HORIZONTAL);
    }

    @Override
    public void setLowLimitLine(LimitLine mLowLimitLine) {
        this.mLowLimitLine = mLowLimitLine;
    }

    @Override
    public void setHighLimitLine(LimitLine mHighLimitLine) {
        this.mHighLimitLine = mHighLimitLine;
    }
}
