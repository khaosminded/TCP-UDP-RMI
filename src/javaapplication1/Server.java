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
    private static boolean then_exit=false;
    
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

    private static String protocol(String inLine){
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
    
    
    private static void TCP(int portNumber){
        System.out.println("TCP server on, listening on port: "+portNumber);
        while(!then_exit){    
            try( 
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            ){
                String inLine,outLine;
                inLine=in.readLine();
                if(!inLine.equals(""))
                    System.out.println("fromclient:"+inLine);
                outLine=protocol(inLine);
                out.println(outLine);
            } 
            catch (IOException e) {
                System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
                System.out.println(e.getMessage());
                break;
            }
      
        }
    }
    
    private static void UDP(int portNumber){
        System.out.println("UDP server on, listening on port: "+portNumber);
        //TODO
             
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