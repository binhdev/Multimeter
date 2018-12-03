package net.multimeter.iot.adc.producer;

import android.graphics.PointF;

import net.multimeter.iot.adc.data.AdcData;
import java.util.List;

public class DataProcessor implements Runnable {
    private AdcData mAdcData;
    private int amount = 1;

    public interface DataReceiverListener{
        void receive(List<PointF> dataPoints);
    }

    DataReceiverListener listener;

    public DataProcessor(AdcData adcData, DataReceiverListener listener) {
        mAdcData = adcData;
        this.listener = listener;
    }

    @Override
    public void run() {
        while (true){
            try {
                listener.receive(mAdcData.get(amount));
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAmount(int value) {
        amount = value;
    }
}
