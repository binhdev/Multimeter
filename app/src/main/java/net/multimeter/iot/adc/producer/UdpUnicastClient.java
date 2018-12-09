package net.multimeter.iot.adc.producer;

import android.util.Log;

import net.multimeter.iot.adc.data.CircularBuffer;
import net.multimeter.iot.adc.helper.ByteConverter;
import net.multimeter.iot.utils.Constants;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpUnicastClient implements Runnable {
    private final int port;
    private final CircularBuffer mCircularBuffer;

    public UdpUnicastClient(int port, CircularBuffer circularBuffer) {
        this.port = port;
        mCircularBuffer = circularBuffer;
    }

    @Override
    public void run() {
        try(DatagramSocket clientSocket = new DatagramSocket(port)){
            clientSocket.setSoTimeout(Constants.SOCKET_TIMEOUT);
            while (true){
                byte[] buffer = new byte[Constants.DATA_BUFFER];
                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);

                clientSocket.receive(datagramPacket);
                putToBuffer(ByteConverter.byteToShort(datagramPacket.getData()));
            }
        }catch (SocketException e){
            Log.e("SocketException: ", e.toString());
        }catch (IOException e){
            Log.e("IOException: ", e.toString());
        }
    }

    private void putToBuffer(short[] values) {
        for(int i = 0; i < values.length; i++) {
            mCircularBuffer.put(values[i]);
        }
    }
}
