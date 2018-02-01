package org.java8;

import org.java8.demo7.MyFunc1;
import org.java8.demo7.Myclass;

public class Demo7 {
    public static void main(String[] args) {
        MyFunc1 myclass= Myclass::new;
        Myclass myclass1=myclass.func(4);
        System.out.println(myclass1.getValue());
    }
}
