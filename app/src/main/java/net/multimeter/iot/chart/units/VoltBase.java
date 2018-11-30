package net.multimeter.iot.chart.units;

import java.util.HashMap;
import java.util.Map;

public enum VoltBase {
    VDIV_10mV(0),
    VDIV_20mV(1),
    VDIV_50mV(2),
    VDIV_100mV(3),
    VDIV_200mV(4),
    VDIV_500mV(5),
    VDIV_1V(6),
    VDIV_2V(7),
    VDIV_5V(8);

    private static final float voltValues[] = {0.01f, 0.02f, 0.05f, 0.1f, 0.2f, 0.5f, 1.0f, 2.0f, 5.0f};

    private int mValue;

    VoltBase(int i) {
        mValue = i;
    }

    /* renamed from: a */
    public int value() {
        return mValue;
    }

    public float getVoltValue() {
        return voltValues[mValue];
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
        return _map.get(value);
    }

    public static int size() {
        return values().length;
    }
}
