package net.multimeter.iot.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import net.multimeter.iot.components.XAxis;
import net.multimeter.iot.utils.RTPointF;
import net.multimeter.iot.utils.Utils;
import net.multimeter.iot.utils.ViewPortHandler;

public class XAxisRenderer extends AxisRenderer {

    protected XAxis mXAxis;

    public XAxisRenderer(ViewPortHandler viewPortHandler, XAxis xAxis) {
        super(viewPortHandler, xAxis);
        this.mXAxis = xAxis;

        mAxisLabelPaint.setColor(Color.WHITE);
        mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
        mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10f));
    }

    @Override
    public void renderAxisLabels(Canvas c) {
        if (!mXAxis.isEnabled() || !mXAxis.isDrawLabelsEnabled())
            return;
        mAxisLabelPaint.setTypeface(mXAxis.getTypeface());
        mAxisLabelPaint.setTextSize(mXAxis.getTextSize());
        mAxisLabelPaint.setColor(mXAxis.getTextColor());

        RTPointF pointF = RTPointF.getInstance(0, 0);

    }

    @Override
    public void renderGridLines(Canvas c) {
        if (!mXAxis.isEnabled())
            return;

        mGridPaint.setColor(mXAxis.getGridColor());
        mGridPaint.setStrokeWidth(mXAxis.getGridLineWidth());
        mGridPaint.setAlpha(50);

        Path path = new Path();
        float f = (mViewPortHandler.getChartWidth()) / mViewPortHandler.XAXIS_UNIT;
        for (int i2 = 1; i2 < mViewPortHandler.XAXIS_UNIT; i2++) {
            float f3 = ((float) i2) * f;
            path.moveTo(f3, 0.0f);
            path.lineTo(f3, mViewPortHandler.getChartHeight());
            c.drawPath(path, mGridPaint);
        }
    }

    @Override
    public void renderAxisLine(Canvas c) {
        if (!mXAxis.isEnabled())
            return;

        mAxisLinePaint.setColor(mXAxis.getAxisLineColor());
        mAxisLinePaint.setStrokeWidth(mXAxis.getAxisLineWidth());
    }

    @Override
    public void renderLimitLines(Canvas c) {
        if(!mXAxis.isEnabled())
            return;

        Path path = new Path();
        mLimitLinePaint.setPathEffect(mXAxis.getLimitLineDashedLine());
        mLimitLinePaint.setStrokeWidth(3.0f);

        path.moveTo(mXAxis.getLowLimitLine().getXoffset(), 0);
        path.lineTo(mXAxis.getLowLimitLine().getXoffset(), mViewPortHandler.getChartHeight());
        c.drawPath(path, mLimitLinePaint);

        path.moveTo(mXAxis.getHighLimitLine().getXoffset(), 0);
        path.lineTo(mXAxis.getHighLimitLine().getXoffset(), mViewPortHandler.getChartHeight());
        c.drawPath(path, mLimitLinePaint);
    }
}
