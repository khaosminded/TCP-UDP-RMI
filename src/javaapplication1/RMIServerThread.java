/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author hanxinlei
 */
public class RMIServerThread  implements RMIinterface{
    //not a real thread-extend class...
    //implements of interface declared on Client
    public RMIServerThread(){}
    
    @Override
    public String protocol(String inLine){
        return Server.protocol(inLine);
    }
    //TODO
}
