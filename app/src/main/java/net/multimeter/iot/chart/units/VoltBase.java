package net.multimeter.iot.chart.units;

import java.util.HashMap;
import java.util.Map;

public enum VoltBase {
    VDIV_10mV(1),
    VDIV_20mV(2),
    VDIV_50mV(3),
    VDIV_100mV(4),
    VDIV_200mV(5),
    VDIV_500mV(6),
    VDIV_1V(7),
    VDIV_2V(8),
    VDIV_5V(9);
    private int mValue;

    VoltBase(int i) {
        mValue = i;
    }

    /* renamed from: a */
    public int value() {
        return mValue;
    }

    private static final Map<Integer, VoltBase> _map = new HashMap<>();
    static
    {
        for (VoltBase difficulty : VoltBase.values())
            _map.put(difficulty.mValue, difficulty);
    }

    /**
     * Get difficulty from value
     * @param value Value
     * @return Difficulty
     */
    public static VoltBase from(int value) {
        if(value > 9  || value < 0)
            value = 1;
        return _map.get(value);
    }
}
