/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author hanxinlei
 */
public class UDPServerThread extends Thread{
    
    DatagramSocket socket;
    DatagramPacket packet;
    String name;
    UDPServerThread(String name,DatagramSocket socket,DatagramPacket packet) {
        super(name);
        this.name=name;
        this.socket=socket;
        this.packet=packet;
    }

    public void run() {
        try {
            System.out.println(name+" is running");
            //construct responding
            byte []buf=Server.protocol(
                    new String(packet.getData()).replaceAll("\0","")
                    ).getBytes();
            // send
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            packet = new DatagramPacket(buf, buf.length, address, port);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{System.out.println(name+" end");}
    }
    
}
