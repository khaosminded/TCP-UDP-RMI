/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
import java.util.*;
/**
 *
 * @author hanxinlei
 */
public class store {
    private Map<String,String> map;
    
    public store(){
        map=new HashMap();
    }
    //I think we only need synchronization on 'write' methods
    //but whatever, performance is not critical here
    public synchronized void  put(String key,String val){
        map.put(key, val); 
    }
    
    public synchronized String get(String key){
        return map.get(key);
    }
    
    public synchronized void del(String key){
        map.remove(key);
    }
    
    public synchronized Iterator<String> list(){
        ArrayList<String> list=new ArrayList();
        Iterator<String> that=map.keySet().iterator();
        while(that.hasNext())
        {
            String key=that.next();
            String val=map.get(key);
            list.add("key:"+key+":value:"+val+":");
        
        }
        return list.iterator();
    }

}
