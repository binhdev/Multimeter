package net.multimeter.iot.adc.data;

import com.google.common.collect.EvictingQueue;

import net.multimeter.iot.chart.units.TimeBase;
import net.multimeter.iot.chart.units.VoltBase;
import net.multimeter.iot.chart.utils.Constants;

public class AdcData {

    private VoltBase eVoltBase;
    private TimeBase eTimeBase;

    private EvictingQueue<Short> mFifoQueue;

    public AdcData(VoltBase voltBase, TimeBase timeBase) {
        eVoltBase = voltBase;
        eTimeBase = timeBase;
        mFifoQueue = EvictingQueue.create(Constants.QUEUE_BUFFER);
    }

    public void add(short[] values) {
        synchronized (mFifoQueue) {
            for (short value : values) {
                mFifoQueue.add(value);
            }
        }
    }

    public short[] get() {
        int amount= 1000;
        short[] values = new short[amount];
        synchronized (mFifoQueue) {
            for(int i=0; i < amount; i++){
                values[i] = mFifoQueue.poll();
            }
        }
        return values;
    }

    public int getSize() {
        return mFifoQueue.size();
    }

    public int getFreeSize() {
        return Constants.QUEUE_BUFFER - mFifoQueue.size();
    }

    public void setVoltBase(VoltBase voltBase) {
        eVoltBase = voltBase;
    }

    public VoltBase getVoltBase() {
        return eVoltBase;
    }

    public void setTimeBase(TimeBase timeBase) {
        eTimeBase = timeBase;
    }

    public TimeBase getTimeBase() {
        return eTimeBase;
    }
}
