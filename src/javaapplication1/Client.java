/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.*;
import java.net.*;

public class Client {
    
    private static void UDP(String host,int hostPort, String command){
        try{
            
            //send 
            DatagramSocket socket = new DatagramSocket();
            byte[] buf = command.getBytes();//constuct datapacket
            DatagramPacket packet = new DatagramPacket(buf, buf.length,
                    InetAddress.getByName(host),hostPort);
            socket.send(packet);
            //receive
            buf = new byte[256];
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            
            System.out.println("server response:"+new String(packet.getData()).replaceAll("\0",""));

        }catch (UnknownHostException e){
            e.printStackTrace();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void TCP(String host,int hostPort,String command){
        try(Socket socket=new Socket(host,hostPort);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            ){
//                System.out.println("TCP Client is running\n"
//                        + "--host=:"+host+" port=:"+hostPort);
                
                out.println(command);
                String fromServer=in.readLine();
                System.out.println("server response: "+fromServer);

            } 
            catch (UnknownHostException e){
                e.printStackTrace();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static void main(String[] args) {
        
        //new Socket
        String host=args[1];
        int hostPort = Integer.parseInt(args[2]);
        /*construct command
        * $ java ­jar GenericNode.jar tc localhost 1234 put a 123
        * args=[[tc|uc],[localhost],[portnum],[put|get|del|store|exit],[!key],[!value]]
        */
        String command="";
        switch(args[3]){
            case "put":command+="1";
                        command+=args[4]+"$"+args[5];
                        break;
            case "get":command+="2";
                        command+=args[4];
                        break;
            case "del":command+="3";
                        command+=args[4];
                        break;
            case "store":command+="4";break;
            case "exit":command+="5";break;
            default: util.usage_info();
        }
        
        
        
        
        
        //transit by diff protocal
        if(args[0].equals("tc"))
            TCP(host,hostPort,command);
        else if(args[0].equals("uc"))
            UDP(host,hostPort,command);
        else
            util.usage_info();
        return;

    }

}