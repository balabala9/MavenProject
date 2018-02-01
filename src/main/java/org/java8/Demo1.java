package org.java8;

import org.java8.bean.Apple;

import java.awt.event.ActionListener;
import java.util.*;

public class Demo1 {
    public static void main(String[] args) {
        //java 8 之前
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("java 8 之前，匿名类");
            }
        }).start();

        //java8 方式 ()->{}代替了整个匿名类() 代表参数，{}body
        new Thread(()->System.out.println("java8 之后，匿名类的表示方法")).start();

        /**
         * lambda 表达式表达形式（只有在匿名类中使用吗）
         *
         */

        Runnable noArg=()->System.out.println("no arg");
        ActionListener oneArgument = event -> System.out.println("button clicked");
        Runnable multiStatement = () -> {
            System.out.print("Hello");
            System.out.println(" World");
        };



        List<Apple> list=new ArrayList<>();
        Collections.sort(list, new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return 0;
            }
        });


        /**
         * Collections.sort() 变为lambda表达式
         */

        List<Integer> list1 = Arrays.asList(1, 12, 21, 3, 13);
        Collections.sort(list1, Comparator.reverseOrder());
        System.out.println(list1);


        Collections.sort(list,(a,b)->a.getNum().compareTo(b.getNum()));
        Collections.sort(list, Comparator.comparing(Apple::getNum));
    }
}
