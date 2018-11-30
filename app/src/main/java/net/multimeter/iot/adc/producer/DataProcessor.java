package net.multimeter.iot.adc.producer;

import android.graphics.PointF;
import net.multimeter.iot.adc.data.AdcData;

public class DataProcessor implements Runnable {
    private AdcData mAdcData;

    public interface DataReceiverListener{
        void receive(PointF[] dataPoints);
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
                short values[] = mAdcData.get();
                PointF dataPoints[] = new PointF[values.length];

                short i = 0;
                for (short value : values) {
                    PointF dataPoint = new PointF(i , value);
                    dataPoints[i] = dataPoint;
                    i++;
                }
                listener.receive(dataPoints);

                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
