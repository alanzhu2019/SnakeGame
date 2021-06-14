package com.alan.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpReceiveDemo01 {
    public static void main(String[] args) throws Exception {

        DatagramSocket socket = new DatagramSocket(6666);

        byte[] bytes = new byte[1024];
        while (true){
            DatagramPacket datagramPacket = new DatagramPacket(bytes,0,bytes.length);

            socket.receive(datagramPacket);

            byte[] data = datagramPacket.getData();
            String stringdata = new String(data, 0,data.length).trim();  //应为byte在创建的时候默认大小1024 在转字符串的时候多余的会转成空格。（String.trin()去空格 或者 直接去接收到数据包的长度packet.legth）
            System.out.println(stringdata);
            if (stringdata.equals("bye")){
                break;
            }
        }
        socket.close();


    }
}
