package net.multimeter.iot.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import net.multimeter.iot.chart.data.Entry;
import net.multimeter.iot.chart.interfaces.IChart;
import net.multimeter.iot.chart.units.TimeBase;
import net.multimeter.iot.chart.units.VoltBase;
import net.multimeter.iot.chart.utils.RTPointF;
import net.multimeter.iot.chart.utils.Transformer;
import net.multimeter.iot.chart.utils.Utils;
import net.multimeter.iot.chart.utils.ViewPortHandler;

public class DataRenderer extends Renderer {

    protected IChart mChart;

    protected Paint mRenderPaint;
    protected Paint mDrawPaint;
    protected Paint mValuePaint;

    public DataRenderer(ViewPortHandler viewPortHandler, IChart chart) {
        super(viewPortHandler);
        mChart = chart;

        mRenderPaint = new Paint();
        mRenderPaint.setColor(Color.YELLOW);
        mRenderPaint.setStrokeWidth(3.0f);
        mRenderPaint.setAlpha(255);
        mRenderPaint.setStyle(Paint.Style.STROKE);

        mDrawPaint = new Paint(Paint.DITHER_FLAG);

        mValuePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setColor(Color.rgb(63, 63, 63));
        mValuePaint.setTextAlign(Paint.Align.CENTER);
        mValuePaint.setTextSize(Utils.convertDpToPixel(9f));
    }

    public void renderData(final Canvas canvas) {
        Path path = new Path();
        RTPointF firtDataPoint = RTPointF.getInstance(0, mViewPortHandler.getChartHeight()/ 2);
        path.moveTo(firtDataPoint.x, firtDataPoint.y);

        for (Entry entry : mChart.getData()) {
            path.lineTo(entry.getX(), mViewPortHandler.getChartHeight()/ 2 + entry.getY());
        }

        canvas.drawPath(path, mRenderPaint);
    }

    public void transform(Transformer transformer, VoltBase voltBase, TimeBase timeBase) {
        for (Entry entry : mChart.getData()) {
            entry.setY(transformer.voltToPixel(entry.getY(), voltBase));
        }

        for (int i = 1; i < mChart.getData().size(); i++){
            Entry entry = mChart.getData().get(i);
            entry.setX(entry.getX() + i * transformer.distanceBetweenPoints(timeBase));
        }
        Log.e("distance", transformer.distanceBetweenPoints(timeBase) + "");
    }
}
