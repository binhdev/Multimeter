package net.multimeter.iot.chart.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import net.multimeter.iot.chart.data.Entry;
import net.multimeter.iot.chart.units.TimeBase;
import net.multimeter.iot.chart.units.VoltBase;

import java.util.List;

public class Utils {

    private static DisplayMetrics mDisplayMetrics;

    public static void init(Context context) {
        if (context == null) {
            Resources res = context.getResources();
            mDisplayMetrics = res.getDisplayMetrics();
        }
    }

    public static float convertDpToPixel(float dp) {

        if (mDisplayMetrics == null) {

            Log.e("Multimeter-Utils",
                    "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before" +
                            " calling Utils.convertDpToPixel(...). Otherwise conversion does not " +
                            "take place.");
            return dp;
        }

        return dp * mDisplayMetrics.density;
    }

    public static float convertPixelsToDp(float px) {

        if (mDisplayMetrics == null) {

            Log.e("Multimeter-Utils",
                    "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before" +
                            " calling Utils.convertPixelsToDp(...). Otherwise conversion does not" +
                            " take place.");
            return px;
        }

        return px / mDisplayMetrics.density;
    }

    public static float convertDataToVolt(int value) {
        return value / Constants.MAX_VALUE_VOLT * Constants.MAX_VOLT;
    }

    public static float getVoltValue(VoltBase voltBase) {
        switch (voltBase) {
            case VDIV_10mV:
                return 0.01f;
            case VDIV_20mV:
                return 0.02f;
            case VDIV_50mV:
                return 0.05f;
            case VDIV_100mV:
                return 0.1f;
            case VDIV_200mV:
                return 0.2f;
            case VDIV_500mV:
                return 0.5f;
            case VDIV_1V:
                return 1.0f;
            case VDIV_2V:
                return 2.0f;
                default:
                    return 1.0f;
        }
    }

    public static float getTimeValue(TimeBase timeBase) {
        switch (timeBase) {
            case HDIV_1mS:
                return 0.001f;
            case HDIV_2mS:
                return 0.002f;
            case HDIV_5mS:
                return 0.005f;
            case HDIV_50mS:
                return 0.05f;
            case HDIV_100mS:
                return 0.1f;
            case HDIV_200mS:
                return 0.2f;
            case HDIV_500mS:
                return 0.5f;
            case HDIV_1S:
                return 1.0f;
            case HDIV_2S:
                return 2.0f;
            case HDIV_5S:
                return 5.0f;
            default:
                return 1.0f;
        }
    }

    public static int getSDKInt() {
        return android.os.Build.VERSION.SDK_INT;
    }
}
