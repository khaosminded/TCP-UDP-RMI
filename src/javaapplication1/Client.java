/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.io.*;
import java.net.*;
import java.util.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
        
public class Client {
    private static void RMI(String host, String command){
        try {
            //Get host and lookup for service
            Registry registry = LocateRegistry.getRegistry(host);
            RMIinterface stub = (RMIinterface) registry.lookup("service");
            //Use remote service
            String outLine = stub.protocol(command);
            System.out.println("response: "+outLine);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
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
            
            System.out.println("server response: "+new String(packet.getData()).replaceAll("\0",""));

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
    private static String protocol(String[] args)
    {   /*construct command
        * $ java ­jar GenericNode.jar tc localhost 1234 put a 123
        * args=[[tc|uc],[localhost],[portnum],[put|get|del|store|exit],[!key],[!value]]
        */
        String command="";
        try{
            int offset=0;
            if(args[0].equals("rmic"))offset=1;
            switch(args[3-offset]){
                case "put":command+="1";
                            command+=args[4-offset]+"$"+args[5-offset];
                            break;
                case "get":command+="2";
                            command+=args[4-offset];
                            break;
                case "del":command+="3";
                            command+=args[4-offset];
                            break;
                case "store":command+="4";break;
                case "exit":command+="5";break;
                default: util.usage_info();
            }
            
        }catch(Exception e){util.usage_info();return null;}
        return command;
    }
    
    public static void main(String[] args) {
        if(args.length<1){
            util.usage_info();
            System.exit(1);
        }        
        
        //transit by diff protocal
        String command=protocol(args);
        switch(args[0]){
            case "tc":
                TCP(args[1],Integer.parseInt(args[2]),command);
                break;
            case "uc":
                UDP(args[1],Integer.parseInt(args[2]),command);
                break;
            case "rmic":
                RMI(args[1],command);
                break;
            default: util.usage_info();
        }

    }

}