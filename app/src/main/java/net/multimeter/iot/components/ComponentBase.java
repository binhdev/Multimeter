package net.multimeter.iot.components;

import android.graphics.Color;
import android.graphics.Typeface;

import net.multimeter.iot.utils.Utils;

public abstract class ComponentBase {

    protected boolean mEnable = true;
    protected float mXoffset = 0.0f;
    protected float mYoffset = 0.0f;
    protected Typeface mTypeface = null;
    protected float mTextSize = Utils.convertDpToPixel(10f);
    protected int mTextColor = Color.BLACK;

    public boolean isEnabled() {
        return mEnable;
    }

    public void setEnable(boolean enable) {
        this.mEnable = enable;
    }

    public float getXoffset() {
        return mXoffset;
    }

    public void setXoffset(float xoffset) {
        this.mXoffset = xoffset;
    }

    public float getYoffset() {
        return mYoffset;
    }

    public void setYoffset(float yoffset) {
        this.mYoffset = yoffset;
    }

    public Typeface getTypeface() {
        return mTypeface;
    }

    public void setTypeface(Typeface typeface) {
        this.mTypeface = typeface;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float size) {
        if (size > 24f)
            size = 24f;
        if (size < 6f)
            size = 6f;

        mTextSize = Utils.convertDpToPixel(size);
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        this.mTextColor = textColor;
    }
}
