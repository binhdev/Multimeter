package net.multimeter.iot.adc.data;

public class CircularBuffer {
    private DataPoint buffer[] = null;
    private int capacity = 0;
    private int writePos = 0;
    private int step = 1;
    private int readPos = 0;

    public CircularBuffer(int maxSize) {
        capacity = maxSize;
        buffer = new DataPoint[capacity];
        for(int i=0; i < capacity; i++){
            buffer[i] = new DataPoint();
        }
    }

    public void put(short item) {
        if(writePos >= capacity){
            writePos = 0;
        }
        buffer[writePos].index = writePos;
        buffer[writePos].value = item;
        writePos++;
    }

    public void reset() {
        writePos = 0;
        readPos = 0;
    }

    public DataPoint[] get(int amount) {
        DataPoint arr[] = new DataPoint[amount];
        for (int i=0; i < amount; i++){
            arr[i] = take();
        }

//        readPos = 0; //(readPos + step - amount) % capacity;
        return arr;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public DataPoint take() {
        readPos = readPos % capacity;

        DataPoint dp = buffer[readPos];
        readPos++;
        return dp;
    }
}
