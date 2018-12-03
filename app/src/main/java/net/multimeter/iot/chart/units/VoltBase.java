package net.multimeter.iot.chart.units;

import java.util.HashMap;
import java.util.Map;

public enum VoltBase {
    VDIV_10mV(0.01f),
    VDIV_20mV(0.02f),
    VDIV_50mV(0.05f),
    VDIV_100mV(0.1f),
    VDIV_200mV(0.2f),
    VDIV_500mV(0.5f),
    VDIV_1V(1.0f),
    VDIV_2V(2.0f),
    VDIV_5V(5.0f);

    private float mValue;

    VoltBase(float i) {
        mValue = i;
    }

    /* renamed from: a */
    public float value() {
        return mValue;
    }

    private static final Map<Float, VoltBase> _map = new HashMap<>();
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
    public static VoltBase from(float value) {
        return _map.get(value);
    }

    public static int size() {
        return values().length;
    }

    public static float[] getRange() {
        float[] arr = new float[_map.size()];
        for(int i=0; i < VoltBase.values().length; i++){
            arr[i] = VoltBase.values()[i].value();
        }
        return arr;
    }
}
