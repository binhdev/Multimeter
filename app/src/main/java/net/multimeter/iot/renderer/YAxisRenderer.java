package net.multimeter.iot.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import net.multimeter.iot.components.YAxis;
import net.multimeter.iot.utils.RTPointF;
import net.multimeter.iot.utils.Utils;
import net.multimeter.iot.utils.ViewPortHandler;

public class YAxisRenderer extends AxisRenderer {

    protected YAxis mYAxis;

    public YAxisRenderer(ViewPortHandler viewPortHandler, YAxis mYAxis) {
        super(viewPortHandler, mYAxis);
        this.mYAxis = mYAxis;

        mAxisLabelPaint.setColor(Color.WHITE);
        mAxisLabelPaint.setTextAlign(Paint.Align.CENTER);
        mAxisLabelPaint.setTextSize(Utils.convertDpToPixel(10f));
    }

    @Override
    public void renderAxisLabels(Canvas c) {
        if (!mYAxis.isEnabled() || !mYAxis.isDrawLabelsEnabled())
            return;
        mAxisLabelPaint.setTypeface(mYAxis.getTypeface());
        mAxisLabelPaint.setTextSize(mYAxis.getTextSize());
        mAxisLabelPaint.setColor(mYAxis.getTextColor());

        RTPointF pointF = RTPointF.getInstance(0, 0);

    }

    @Override
    public void renderGridLines(Canvas c) {
        if (!mYAxis.isEnabled())
            return;

        mGridPaint.setColor(mYAxis.getGridColor());
        mGridPaint.setStrokeWidth(mYAxis.getGridLineWidth());
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
        if (!mYAxis.isEnabled())
            return;

        mAxisLinePaint.setColor(mYAxis.getAxisLineColor());
        mAxisLinePaint.setStrokeWidth(mYAxis.getAxisLineWidth());
    }

    @Override
    public void renderLimitLines(Canvas c) {
        if(!mYAxis.isEnabled())
            return;

        Path path = new Path();
        mLimitLinePaint.setPathEffect(mYAxis.getLimitLineDashedLine());
        mLimitLinePaint.setStrokeWidth(3.0f);

        path.moveTo(0, mYAxis.getLowLimitLine().getYoffset());
        path.lineTo(mViewPortHandler.getChartWidth(), mYAxis.getLowLimitLine().getYoffset());
        c.drawPath(path, mLimitLinePaint);

        path.moveTo(0, mYAxis.getHighLimitLine().getYoffset());
        path.lineTo(mViewPortHandler.getChartWidth(), mYAxis.getHighLimitLine().getYoffset());
        c.drawPath(path, mLimitLinePaint);
    }
}
