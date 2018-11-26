package net.multimeter.iot.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import net.multimeter.iot.chart.components.YAxis;
import net.multimeter.iot.chart.utils.RTPointF;
import net.multimeter.iot.chart.utils.Utils;
import net.multimeter.iot.chart.utils.ViewPortHandler;

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

        float f = (mViewPortHandler.getChartHeight()) / mViewPortHandler.YAXIS_UNIT;
        for (int i = 1; i < mViewPortHandler.YAXIS_UNIT; i++) {
            float f2 = ((float) i) * f;
            path.moveTo(0.0f, f2);
            path.lineTo(mViewPortHandler.getChartWidth(), f2);
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
