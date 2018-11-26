package net.multimeter.iot.network.producer;

import android.graphics.PointF;

import net.multimeter.iot.network.helper.ByteConverter;

import java.util.concurrent.BlockingQueue;

public class DataProcessor implements Runnable {
    private final BlockingQueue<byte[]> messageQueue;

    public interface DataReceiverListener{
        void receive(PointF[] dataPoints);
    }

    DataReceiverListener listener;

    public DataProcessor(BlockingQueue<byte[]> messageQueue, DataReceiverListener listener) {
        this.messageQueue = messageQueue;
        this.listener = listener;
    }

    @Override
    public void run() {
        while (true){
            try {
                byte[] rawData = this.messageQueue.take();
                short[] values = ByteConverter.byteToShort(rawData);
                PointF dataPoints[] = new PointF[values.length];

                short i = 0;
                for (short value : values) {
                    PointF dataPoint = new PointF(i , value);
                    dataPoints[i] = dataPoint;
                    i++;
                }
                listener.receive(dataPoints);

                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
