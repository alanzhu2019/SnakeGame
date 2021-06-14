package com.alan.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class TalkSend implements Runnable{

    DatagramSocket socket =null;
    BufferedReader reader =null;
    private int fromPort;
    private String toIp;
    private int toPort;

    public TalkSend(int fromPort, String toIp, int toPort) {
        this.fromPort = fromPort;
        this.toIp = toIp;
        this.toPort = toPort;

        try {
             socket = new DatagramSocket(fromPort);
             reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public TalkSend() {

    }

    @Override
    public void run() {


        while (true){

            try {
                String readLine = reader.readLine();
                byte[] readLineBytes = readLine.getBytes(StandardCharsets.UTF_8);

                DatagramPacket packet = new DatagramPacket(readLineBytes,0,readLineBytes.length,new InetSocketAddress(this.toIp,this.toPort));

                socket.send(packet);

                if (readLine.equals("bye")){
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        socket.close();
    }
}
