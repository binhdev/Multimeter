package net.multimeter.iot.chart.utils;

import android.graphics.Matrix;
import android.graphics.Path;

import net.multimeter.iot.chart.data.Entry;
import java.util.List;

public class Transformer {

    protected Matrix mMatrixValueToPx = new Matrix();

    protected ViewPortHandler mViewPortHandler;

    public Transformer(ViewPortHandler viewPortHandler){
        this.mViewPortHandler = viewPortHandler;
    }

    public void prepareMatrixValuePx(float xChartMin, float deltaX, float deltaY, float yChartMin) {

        float scaleX = mViewPortHandler.contentWidth() / deltaX;
        float scaleY = mViewPortHandler.contentHeight() / deltaY;

        if (Float.isInfinite(scaleX)) {
            scaleX = 0;
        }
        if (Float.isInfinite(scaleY)) {
            scaleY = 0;
        }

        // setup all matrices
        mMatrixValueToPx.reset();
        mMatrixValueToPx.postTranslate(-xChartMin, -yChartMin);
        mMatrixValueToPx.postScale(scaleX, -scaleY);
    }

    public void pathValueToPixel(Path path){
        path.transform(mMatrixValueToPx);
    }

    public float calculateDistance(List<Entry> data){
        int count = data.size();
        return mViewPortHandler.getChartWidth() - count > 0 ? mViewPortHandler.getChartWidth() / (count-1) : 1;
    }

    public void pixelsToValue(float[] pixels){

    }

    public float calculateVolt(float lower, float higher, float scale){
        float distance = Math.abs(higher - lower);
        return pixelToYAxisValue(distance, scale);
    }

    public float calculateTime(float lower, float higher, float scale){
        float distance = Math.abs(higher - lower);
        return xAxisValueToPixel(distance, scale);
    }

    public float yAxisValueToPixel(float value, float scale) {
        return value / scale * pixelPerYAxisUnit();
    }

    public float pixelToYAxisValue(float value, float scale) {
        return value / pixelPerYAxisUnit() * scale;
    }

    private float pixelPerYAxisUnit() {
        return mViewPortHandler.getChartHeight() / mViewPortHandler.YAXIS_UNIT;
    }

    public float xAxisValueToPixel(float value, float scale) {
        return value / scale * pixelPerXAxisUnit();
    }

    public float pixelToXAxisValue(float value, float scale) {
        return value / pixelPerXAxisUnit() * scale;
    }

    private float pixelPerXAxisUnit() {
        return mViewPortHandler.getChartWidth() / mViewPortHandler.XAXIS_UNIT;
    }

    public float distanceBetweenPoints(float scale) {
        return pixelPerXAxisUnit() / (Constants.NUMBER_POINT_PER_SECOND * scale);
    }

}
