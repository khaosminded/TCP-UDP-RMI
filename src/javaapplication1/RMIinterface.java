/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.rmi.Remote; 
import java.rmi.RemoteException; 
/**
 *
 * @author hanxinlei
 */
public interface RMIinterface extends Remote{

    public String protocol(String inLine)throws RemoteException;

}
