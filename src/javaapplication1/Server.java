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

public class Server {
    private static store st=new store();
    static boolean then_exit=false;
    
    private static String put(String key,String val){
        st.put(key, val);
        return "put key="+key;
    }
    private static String get(String key){
        String val=st.get(key);
        if(key==null)return "invalid_key"; 
        return "get key="+key+" get val="+val;
    }
    private static String del(String key){
        st.del(key);
        return "delete key="+key;
    }
    private static String store(){
        Iterator<String> it=st.list();
        String list="";
        while(it.hasNext()){
            list+=it.next();
        }
        return list;
    }
    private static String exit(){
        then_exit=true;
        return "<the server then exits>";
    }

    public static String protocol(String inLine){
        if(!inLine.equals(""))
                    System.out.println("fromclient:"+inLine);
        String [] index;
        index=inLine.substring(1).split("\\$");
        String response="";
        switch(inLine.charAt(0))
        {
            case '1':
                response=put(index[0],index[1]);
                break;
            case '2':
                response=get(index[0]);
                break;                
            case '3':
                response=del(index[0]);
                break;
            case '4':
                response=store();
                break;
            case '5':
                response=exit();
                break;
            default:
                util.usage_info();
        }
        
        return response;
    }
    
    private static void RMI(){/*TODO*/};
    
    private static void TCP(int portNumber){
        System.out.println("TCP server on, trying to listen port: "+portNumber);
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            Integer threadId=0;
            while (!then_exit) {
                //each client connection(socket) create a thread
                threadId++;
                new TCPServerThread("TCPThread"+threadId.toString(),
                        serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
        }
    }
    
    private static void UDP(int portNumber){
        System.out.println("UDP server on, trying to listen port: "+portNumber);
        try{
        //****UDP SERVERS LISTEN ON PORT A, SHOULD SEND ON PORT A+1***//
        //Why?Im not sure.
            Integer threadId=0;
            DatagramSocket socket_r = new DatagramSocket(portNumber);
            DatagramSocket socket_s = new DatagramSocket(portNumber+1);
            while(!then_exit)
            {//each packet a thread. Seems create thread too frequently...
                threadId++;
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket_r.receive(packet);
                new UDPServerThread("UDPThread"+threadId.toString(),
                        socket_s,packet).start();
            }
        }catch(IOException e){
            System.out.println("Exception caught when trying to listen on port "
                        + portNumber);
        }
    }
    
    public static void main(String[] args) {
        int portNumber = Integer.parseInt(args[1]);
        
        if(args[0].equals("ts"))
            TCP(portNumber);
        else if(args[0].equals("us"))
            UDP(portNumber);
        else 
            util.usage_info();
    }

}