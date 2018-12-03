package net.multimeter.iot.chart.units;

import java.util.HashMap;
import java.util.Map;

public enum TimeBase {
    HDIV_1mS(0.001f),
    HDIV_2mS(0.002f),
    HDIV_5mS(0.005f),
    HDIV_10mS(0.01f),
    HDIV_20mS(0.02f),
    HDIV_50mS(0.05f),
    HDIV_100mS(0.1f),
    HDIV_200mS(0.2f);

    /* renamed from: I */
    private static TimeBase[] values;
    /* renamed from: J */
    private float mValue;

    static {
        values = TimeBase.values();
    }

    private TimeBase(float i) {
        this.mValue = i;
    }

    /* renamed from: a */
    public float value() {
        return this.mValue;
    }


    private static final Map<Float, TimeBase> _map = new HashMap<>();
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
    public static TimeBase from(float value) {
        return _map.get(value);
    }

    public static int size() {
        return values().length;
    }

    public static float[] getRange() {
        float[] arr = new float[_map.size()];
        for(int i=0; i < TimeBase.values().length; i++){
            arr[i] = TimeBase.values()[i].value();
        }
        return arr;
    }
}
