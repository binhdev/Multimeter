package net.multimeter.iot.chart.utils;

public class Helper {
    public static String formatValue(String value, String unit) {
        return String.format("%s %s", value, unit);
    }

    public static String formatTime(String time, String unit) {
        return String.format("%s %s", time, unit);
    }
}
