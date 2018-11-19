package net.multimeter.iot.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

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

            Log.e("MPChartLib-Utils",
                    "Utils NOT INITIALIZED. You need to call Utils.init(...) at least once before" +
                            " calling Utils.convertPixelsToDp(...). Otherwise conversion does not" +
                            " take place.");
            return px;
        }

        return px / mDisplayMetrics.density;
    }
}