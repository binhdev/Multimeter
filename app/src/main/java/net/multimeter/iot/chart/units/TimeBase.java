package net.multimeter.iot.chart.units;

import java.util.HashMap;
import java.util.Map;

public enum TimeBase {
    HDIV_1mS(0),
    HDIV_2mS(1),
    HDIV_5mS(2),
    HDIV_10mS(3),
    HDIV_20mS(4),
    HDIV_50mS(5),
    HDIV_100mS(6),
    HDIV_200mS(7),
    HDIV_500mS(8),
    HDIV_1S(9),
    HDIV_2S(10),
    HDIV_5S(11);

    private static final float timeValues[] = {0.001f, 0.002f, 0.005f, 0.01f, 0.02f, 0.05f, 0.1f, 0.2f, 0.5f, 1f, 2f, 5f};
    /* renamed from: I */
    private static TimeBase[] values;
    /* renamed from: J */
    private int mValue;

    static {
        values = TimeBase.values();
    }

    private TimeBase(int i) {
        this.mValue = i;
    }

    /* renamed from: a */
    public int value() {
        return this.mValue;
    }

    public float getTimeValue() {
        return timeValues[mValue];
    }

    private static final Map<Integer, TimeBase> _map = new HashMap<>();
    static
    {
        for (TimeBase timeBase : TimeBase.values())
            _map.put(timeBase.mValue, timeBase);
    }

    /**
     * Get difficulty from value
     * @param value Value
     * @return Difficulty
     */
    public static TimeBase from(int value) {
        return _map.get(value);
    }

    public static int size() {
        return values().length;
    }
}
