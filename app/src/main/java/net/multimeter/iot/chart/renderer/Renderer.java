package net.multimeter.iot.chart.renderer;

import net.multimeter.iot.chart.utils.ViewPortHandler;

public abstract class Renderer {
    protected ViewPortHandler mViewPortHandler;

    public Renderer(ViewPortHandler viewPortHandler){
        this.mViewPortHandler = viewPortHandler;
    }
}
