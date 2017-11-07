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
public class TCPServerThread extends Thread{
    
    private int portNumber;
    private Socket clientSocket;
    private String name;
    TCPServerThread(String name,Socket clientSocket)
    {
        super(name);
        this.name=name;
        this.clientSocket=clientSocket;
        this.portNumber=portNumber;
    }
    public void run()
    {
        System.out.println(name+" is running");
        try( 
            PrintWriter out =
                new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        ){
            String inLine,outLine;
            inLine=in.readLine();

            outLine=Server.protocol(inLine);
            out.println(outLine);
        } 
        catch (IOException e) {
            System.out.println("Exception caught when listening for a connection");
            System.out.println(e.getMessage());
        }
        System.out.println(name+" end");
    }
}
