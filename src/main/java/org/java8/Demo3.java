package org.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Demo3 {
    public static void main(String[] args) {
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        filter(languages,(str)->str.startsWith("C"));


        //.Predicate 允许将两个或更多的 Predicate 合成一个
        Predicate<String> p=(str)->str.startsWith("J");
        Predicate<String> p2=(str)->str.startsWith("S");

        filter(languages,p.or(p2));

    }
    public static void filter(List<String> names,Predicate<String> condition){
        for(String name:names){
            if(condition.test(name)){
                System.out.println(name+"");
            }
        }
    }
}
