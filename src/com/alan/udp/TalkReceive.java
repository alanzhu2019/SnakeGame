package com.alan.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkReceive implements Runnable{
    DatagramSocket socket =null;
    private int fromPort;
    private String masFrom;

    public TalkReceive(int fromPort,String masFrom) {
        this.fromPort = fromPort;
        this.masFrom = masFrom;
        try {
            socket = new DatagramSocket(fromPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void run() {


        byte[] bytes = new byte[1024];
        while (true){
            DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length);

            try {
                socket.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] data = datagramPacket.getData();
            String stringdata = new String(data, 0,datagramPacket.getLength());  //应为byte在创建的时候默认大小1024 在转字符串的时候多余的会转成空格。（String.trin()去空格 或者 直接去接收到数据包的长度packet.legth）
            System.out.println(masFrom +":" + stringdata);
            if (stringdata.equals("bye")){
                break;
            }
        }
        socket.close();
    }
}
