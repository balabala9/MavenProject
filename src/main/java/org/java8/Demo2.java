package org.java8;

import java.util.Arrays;
import java.util.List;

public class Demo2 {

    public static void main(String[] args) {
        List<String> features= Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");

        //java8 之前
        for(String feature:features){
            System.out.println(feature);
        }

        //java8 之后
        System.out.println("------------------------");
        features.forEach(n -> System.out.println(n));

        System.out.println("------------------------");
        //java8 的方法引用::
        features.forEach(System.out::println);

    }

}
