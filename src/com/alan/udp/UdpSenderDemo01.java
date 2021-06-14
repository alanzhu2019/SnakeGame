package com.alan.udp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class UdpSenderDemo01 {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(9999);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true){
            String readLine = reader.readLine();
            byte[] readLineBytes = readLine.getBytes(StandardCharsets.UTF_8);

            DatagramPacket packet = new DatagramPacket(readLineBytes,0,readLineBytes.length,new InetSocketAddress("localhost",6666));

            socket.send(packet);

            if (readLine.equals("bye")){
                break;
            }
        }
        socket.close();

    }
}
