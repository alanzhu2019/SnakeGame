package com.alan.snake;

import com.sun.jmx.snmp.InetAddressAcl;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
    public static void main(String[] args) throws UnknownHostException {
        int[] ip = new int[20];

        InetAddress[] inetAddress = InetAddress.getAllByName("alancode.ml");

        for (int i = 0; i < inetAddress.length; i++) {
            System.out.println(inetAddress[i]);
        }

    }
}
