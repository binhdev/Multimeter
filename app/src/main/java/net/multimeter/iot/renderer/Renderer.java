package net.multimeter.iot.renderer;

import net.multimeter.iot.utils.ViewPortHandler;

public abstract class Renderer {
    protected ViewPortHandler mViewPortHandler;

    public Renderer(ViewPortHandler viewPortHandler){
        this.mViewPortHandler = viewPortHandler;
    }
}
