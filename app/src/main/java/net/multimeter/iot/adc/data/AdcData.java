package net.multimeter.iot.adc.data;

import android.graphics.PointF;

import com.google.common.collect.EvictingQueue;
import net.multimeter.iot.chart.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class AdcData {

    private EvictingQueue<Short> mFifoQueue;
    private List<PointF> list = new ArrayList<>();

    public AdcData() {
        mFifoQueue = EvictingQueue.create(Constants.QUEUE_BUFFER);
    }

    public void add(short[] values) {
        synchronized (mFifoQueue) {
            for(int i = 0;  i < values.length; i++){
                mFifoQueue.add(values[i]);
            }
        }
    }

    public List<PointF> get(int amount) {
        list.clear();
        if(mFifoQueue.size() > amount)
            synchronized (mFifoQueue) {
                for(int i = 0; i < amount; i++){
                    list.add(new PointF(i, mFifoQueue.remove()));
                }
            }
        return list;
    }

    public int getSize() {
        return mFifoQueue.size();
    }

    public int getFreeSize() {
        return Constants.QUEUE_BUFFER - mFifoQueue.size();
    }
}
