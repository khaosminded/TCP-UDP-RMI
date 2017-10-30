/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author hanxinlei
 */
public class util {
    public static void usage_info()
    {
        String info="usage_info TODO";
        System.out.print(info);
    }
    public static void main(String[] args) {
        //testing java features
        String a="a";
        String substring[] = a.substring(1).split("\\$");
        System.out.println(substring[0]);
        //System.out.println(substring[1]);
        
    }
}
