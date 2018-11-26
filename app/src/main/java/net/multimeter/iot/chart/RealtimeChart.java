package net.multimeter.iot.chart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import net.multimeter.iot.chart.components.HUD;
import net.multimeter.iot.chart.components.XAxis;
import net.multimeter.iot.chart.components.YAxis;
import net.multimeter.iot.chart.data.Entry;
import net.multimeter.iot.chart.interfaces.IChart;
import net.multimeter.iot.chart.renderer.DataRenderer;
import net.multimeter.iot.chart.renderer.HUDRenderer;
import net.multimeter.iot.chart.renderer.XAxisRenderer;
import net.multimeter.iot.chart.renderer.YAxisRenderer;
import net.multimeter.iot.chart.units.TimeBase;
import net.multimeter.iot.chart.units.VoltBase;
import net.multimeter.iot.chart.utils.Transformer;
import net.multimeter.iot.chart.utils.Utils;
import net.multimeter.iot.chart.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class RealtimeChart extends SurfaceView implements SurfaceHolder.Callback, IChart {

    private final static String TAG = RealtimeChart.class.getName();

    private SurfaceHolder mSurfaceHolder;
    private ViewPortHandler mViewPortHandler = new ViewPortHandler();
    protected Transformer mTransformer;

    protected XAxis mXAxis;
    protected XAxisRenderer mXAxisRenderer;

    protected YAxis mYAxis;
    protected YAxisRenderer mYAxisRenderer;

    protected DataRenderer mDataRenderer;

    protected HUD mHudAmplitude;
    protected HUDRenderer mHUDAmplitudeRender;

    private List<Entry> mData = new ArrayList<>();

    private ScaleGestureDetector mScaleGestureDetector;

    VoltBase eVoltBase = VoltBase.VDIV_1V;
    TimeBase eTimeBase = TimeBase.HDIV_100mS;

    /**
     * mValue: Volt, Omega
     * mTime: time
     */
    private int mVoltBase = eVoltBase.value();
    private int mTimeBase = eTimeBase.value();

    IZoomAction mZoomAction;

    public RealtimeChart(Context context) {
        super(context);
        init();
    }

    public RealtimeChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        Utils.init(getContext());

        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mTransformer = new Transformer(mViewPortHandler);
        mTransformer.prepareMatrixValuePx(0,
                mViewPortHandler.getChartWidth(),
                mViewPortHandler.getChartHeight(),
                0);

        mXAxis = new XAxis();
        mXAxisRenderer = new XAxisRenderer(mViewPortHandler, mXAxis);

        mYAxis = new YAxis();
        mYAxisRenderer = new YAxisRenderer(mViewPortHandler, mYAxis);

        mDataRenderer = new DataRenderer(mViewPortHandler, this);


        mHudAmplitude = new HUD();
        List<String> values = new ArrayList<>();
        mHudAmplitude.setValues(values);
        mHudAmplitude.setTextSize(32.0f);
        mHUDAmplitudeRender = new HUDRenderer(mViewPortHandler, mHudAmplitude);

        mScaleGestureDetector = new ScaleGestureDetector(getContext(), new SimpleScaleGesture());

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        mViewPortHandler.setChartDimens(width, height);
        notifityDataChange();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mScaleGestureDetector.onTouchEvent(event);
        return true;
    }

    public void notifityDataChange(){
        if (mSurfaceHolder != null && mSurfaceHolder.getSurface().isValid()) {
            final Canvas lockCanvas = mSurfaceHolder.lockCanvas();
            if (lockCanvas != null) {
                renderBackground(lockCanvas);
                renderAxis(lockCanvas);
                renderData(lockCanvas);

                updateHUD();
                mHUDAmplitudeRender.renderHUD(lockCanvas);
                mSurfaceHolder.unlockCanvasAndPost(lockCanvas);
            }
        }
    }

    /**
     * Place for render
     */

    private void renderBackground(Canvas canvas){
        canvas.drawColor(Color.DKGRAY);
    }

    private void renderAxis(Canvas canvas) {
        //Render XAxis
        mXAxisRenderer.renderGridLines(canvas);
        mXAxisRenderer.renderAxisLabels(canvas);
        mXAxisRenderer.renderAxisLine(canvas);
        mXAxisRenderer.renderLimitLines(canvas);
//        mXAxisRenderer.renderMeasurementLine(canvas);

        //Render YAxis
        mYAxisRenderer.renderGridLines(canvas);
        mYAxisRenderer.renderAxisLabels(canvas);
        mYAxisRenderer.renderAxisLine(canvas);
        mYAxisRenderer.renderLimitLines(canvas);
//        mYAxisRenderer.renderMeasurementLine(canvas);
    }

    private void renderData(Canvas canvas) {
        mDataRenderer.transform(mTransformer, eVoltBase, eTimeBase);
        mDataRenderer.renderData(canvas);
    }

    private void updateHUD(){
        List<String> values = new ArrayList<>();
        float time = mTransformer.calculateTime(mXAxis.getHighLimitLine().getXoffset(), mXAxis.getLowLimitLine().getXoffset(), eTimeBase);
        float volt = mTransformer.calculateVolt(mYAxis.getHighLimitLine().getYoffset(), mYAxis.getLowLimitLine().getYoffset(), eVoltBase);
        values.add("CH1 - Volt: " + volt );
        values.add("CH1 - Time: " + time);
        values.add("CH1: 100mv");
        mHudAmplitude.setValues(values);
    }

    public HUD getHudAmplitude(){
        return mHudAmplitude;
    }

    public XAxis getXAxis() {
        return mXAxis;
    }

    public YAxis getYAxis() {
        return mYAxis;
    }

    public void setData(List<Entry> data) {
        mData.clear();
        mData.addAll(data);
    }

    @Override
    public List<Entry> getData() {
        return mData;
    }

    /**
     * Place zoom
     */

    private class SimpleScaleGesture extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        private SimpleScaleGesture(){

        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            return super.onScaleBegin(detector);
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            return super.onScale(detector);
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            super.onScaleEnd(detector);
            float scaleFactor = detector.getScaleFactor();

            float spanX = Math.abs(detector.getCurrentSpanX() - detector.getPreviousSpanX());
            float spanY = Math.abs(detector.getCurrentSpanY() - detector.getPreviousSpanY());

            /**
             * Vertical zoom - scale time
             */
            if(mZoomAction == null)
                return;

            if(spanX > spanY){
                if(scaleFactor > 1){
                    if(mTimeBase > 10) return;
                    mTimeBase += 1;
                }else{
                    if(mTimeBase <= 1) return;
                    mTimeBase -= 1;
                }
                eTimeBase = TimeBase.from(mTimeBase);
                mZoomAction.scaleTime(eTimeBase.toString());
            }else{
                /**
                 * Horizontal zoom - scale value
                 */
                if(scaleFactor > 1){
                    if(mVoltBase > 9) return;
                    mVoltBase += 1;
                }else{
                    if(mVoltBase <= 1) return;
                    mVoltBase -= 1;
                }
                eVoltBase = VoltBase.from(mVoltBase);
                mZoomAction.scaleValue(eVoltBase.toString());
            }

        }
    }

    public void setZoomActionListener(IZoomAction zoomAction) {
        mZoomAction = zoomAction;
    }

    public interface IZoomAction {
        void scaleValue(String value);
        void scaleTime(String value);
    }
}