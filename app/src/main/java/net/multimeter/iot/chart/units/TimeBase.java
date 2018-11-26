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
        if(value > 11  || value < 0)
            value = 0;
        return _map.get(value);
    }
}
