package org.java.enums;

public class TupleTest {
    static TwoTuple<String, Integer> f() {
        return new TwoTuple<>("li", 23);
    }


    public static void main(String[] args) {
        TwoTuple<String, Integer> t1 = f();
        System.out.println(t1);
    }
}
