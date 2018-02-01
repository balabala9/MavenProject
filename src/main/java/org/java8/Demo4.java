package org.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Demo4 {
    public static void main(String[] args) {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }

        System.out.println("=======================");
        costBeforeTax.stream().map(x->x+.12*x).forEach(x->System.out.println(x));

        List<Double> list2=costBeforeTax.stream().map(x->x+.12*x).collect(Collectors.toList());
        System.out.println(list2);

        Double totel=costBeforeTax.stream().map(x->x+.12*x).reduce((x,y)->x+y).get();
        System.out.println(totel);


    }
}
