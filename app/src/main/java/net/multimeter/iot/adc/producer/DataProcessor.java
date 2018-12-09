package net.multimeter.iot.adc.producer;

import net.multimeter.iot.adc.data.CircularBuffer;
import net.multimeter.iot.adc.data.DataPoint;

public class DataProcessor implements Runnable {
    private final CircularBuffer mCircularBuffer;
    private int amount = 2400;
    private int delay = 15; //milisecond
    private volatile boolean isRunning = true;

    public interface DataReceiverListener{
        void receive(DataPoint[] dataPoints);
    }

    DataReceiverListener listener;

    public DataProcessor(CircularBuffer circularBuffer, DataReceiverListener listener) {
        mCircularBuffer = circularBuffer;
        this.listener = listener;
    }

    @Override
    public void run() {
        while (isRunning){
            try {
                listener.receive(mCircularBuffer.get(amount));
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setAmount(int value) {
        amount = value;
    }

    public void setTimeDelay(int time) {
        delay = time;
    }

    public void stop() {
        isRunning = false;
    }
}
