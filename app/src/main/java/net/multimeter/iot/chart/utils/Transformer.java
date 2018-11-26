package net.multimeter.iot.chart.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.util.Log;

import net.multimeter.iot.chart.data.Entry;
import net.multimeter.iot.chart.units.TimeBase;
import net.multimeter.iot.chart.units.VoltBase;

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

    public float calculateVolt(float lower, float higher, VoltBase voltBase){
        float distance = Math.abs(higher - lower);
        return pixelToVolt(distance, voltBase);
    }

    public float calculateTime(float lower, float higher, TimeBase timeBase){
        float distance = Math.abs(higher - lower);
        return pixelToTime(distance, timeBase);
    }

    public float voltToPixel(float value, VoltBase voltBase) {
        return value / Utils.getVoltValue(voltBase) * pixelPerValueUnit();
    }

    public float pixelToVolt(float value, VoltBase voltBase) {
        return value / pixelPerValueUnit() * Utils.getVoltValue(voltBase);
    }

    private float pixelPerValueUnit() {
        return mViewPortHandler.getChartHeight() / mViewPortHandler.YAXIS_UNIT;
    }

    public float timeToPixel(float value, TimeBase timeBase) {
        return value / Utils.getTimeValue(timeBase) * pixelPerTimeUnit();
    }

    public float pixelToTime(float value, TimeBase timeBase) {
        return value / pixelPerTimeUnit() * Utils.getTimeValue(timeBase);
    }

    private float pixelPerTimeUnit() {
        return mViewPortHandler.getChartWidth() / mViewPortHandler.XAXIS_UNIT;
    }

    public float distanceBetweenPoints(TimeBase timeBase) {
        return pixelPerTimeUnit() / Constants.NUMBER_POINT_PER_SECOND / Utils.getTimeValue(timeBase);
    }

}
