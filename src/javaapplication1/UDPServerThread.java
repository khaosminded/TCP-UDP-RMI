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
    
    private DatagramSocket socket_r = null,socket_s=null;
    UDPServerThread(String name,int port_listen) {
        super(name);
        System.out.println(name+" is running");
        try{
        //****UDP SERVERS LISTEN ON PORT A, SHOULD SEND ON PORT A+1***//
        //Why?Im not sure.
            socket_r = new DatagramSocket(port_listen);
            socket_s = new DatagramSocket(port_listen+1);
        }
        catch(IOException e){
            System.out.println("Exception caught when trying to listen on port "
                        + port_listen);
        }
    }

   
    
    public void run() {

        while (!Server.then_exit) {
            try {
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                //congestive receive
                socket_r.receive(packet);
                //construct responding
                buf=Server.protocol(new String(packet.getData()).replaceAll("\0","")).getBytes();
		// send
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket_s.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        socket_s.close();
        socket_r.close();
    }
    
}
