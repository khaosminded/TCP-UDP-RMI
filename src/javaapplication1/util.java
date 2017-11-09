/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.net.DatagramPacket;

/**
 *
 * @author hanxinlei
 */
public class util {
    public static void usage_info()
    {
        String info="Wrong command, Usage Info:\nClient:\n" +
            "uc/tc <address> <port> put <key> <msg>  UDP/TCP CLIENT: Put an object into store\n" +
            "uc/tc <address> <port> get <key>  UDP/TCP CLIENT: Get an object from store by key\n" +
            "uc/tc <address> <port> del <key>  UDP/TCP CLIENT: Delete an object from store by key\n" +
            "uc/tc <address> <port> store  UDP/TCP CLIENT: Display object store\n" +
            "uc/tc <address> <port> exit  UDP/TCP CLIENT: Shutdown server\n" +
            "rmic <address> put <key> <msg>  RMI CLIENT: Put an object into store\n" +
            "rmic <address> get <key>  RMI CLIENT: Get an object from store by key\n" +
            "rmic <address> del <key>  RMI CLIENT: Delete an object from store by key\n" +
            "rmic <address> store  RMI CLIENT: Display object store\n" +
            "rmic <address> exit  RMI CLIENT: Shutdown server\n" +
            "Server:\n" +
            "us/ts <port>  UDP/TCP/TCP-and-UDP SERVER: run server on <port>.\n" +
            "tus <tcpport> <udpport>  TCP-and-UDP SERVER: run servers on <tcpport> and <udpport> sharing same key-value store.\n" +
            "alls <tcpport> <udpport>  TCP, UDP, and RMI SERVER: run servers on <tcpport> and <udpport> sharing same key-value store.\n" +
            "rmic  RMI Server.";
        System.out.print(info);
    }

}
