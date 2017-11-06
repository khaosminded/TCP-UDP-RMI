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
    
    public void put(String key,String val){
        map.put(key, val); 
    }
    
    public String get(String key){
        return map.get(key);
    }
    
    public void del(String key){
        map.remove(key);
    }
    
    public Iterator<String> list(){
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
