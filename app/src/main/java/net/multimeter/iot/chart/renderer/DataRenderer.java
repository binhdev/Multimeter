package net.multimeter.iot.chart.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import net.multimeter.iot.chart.data.Entry;
import net.multimeter.iot.chart.interfaces.IChart;
import net.multimeter.iot.chart.utils.RTPointF;
import net.multimeter.iot.chart.utils.Transformer;
import net.multimeter.iot.chart.utils.Utils;
import net.multimeter.iot.chart.utils.ViewPortHandler;

import java.util.List;

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
        List<Entry> listData = mChart.getData();
        if(listData.size() == 0) return;

        Path path = new Path();
        Entry first = listData.get(0);
        RTPointF firtDataPoint = RTPointF.getInstance(first.getX(), mViewPortHandler.getChartHeight()/ 2 + first.getY());
        path.moveTo(firtDataPoint.x, firtDataPoint.y);

        for (int i = 1; i < listData.size(); i++){
            Entry entry = listData.get(i);
            path.lineTo(entry.getX(), mViewPortHandler.getChartHeight()/ 2 + entry.getY());
        }

        canvas.drawPath(path, mRenderPaint);
    }

    public void transform(Transformer transformer,float xScale, float yScale) {
        List<Entry> listData = mChart.getData();
        if(listData.size() == 0) return;
        Entry first = listData.get(0);

        for (int i = 1; i < listData.size(); i++){
            Entry entry = listData.get(i);
            entry.setX((int)(i * transformer.distanceBetweenPoints(xScale)));
        }

        for (Entry entry : listData) {
            entry.setY(transformer.yAxisValueToPixel(entry.getY(), yScale));
        }
    }

    public void translate(float speed) {
        List<Entry> listData = mChart.getData();
        for (int i = 1; i < listData.size(); i++){
            Entry entry = listData.get(i);
            entry.setX(entry.getX() + speed);
        }
    }
}
