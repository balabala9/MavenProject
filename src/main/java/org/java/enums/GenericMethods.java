package org.java.enums;

public class GenericMethods {
    public static void main(String[] args) {
        GenericMethods genericMethods = new GenericMethods();
        genericMethods.f("");
        genericMethods.f(1);
        genericMethods.f(1.0);
    }

    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }
}
