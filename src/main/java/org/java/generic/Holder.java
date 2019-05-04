package org.java.generic;

public class Holder<T> {
    private T a;

    public Holder(T a) {
        this.a = a;
    }

    public static void main(String[] args) {

    }

    public void set(T a) {
        this.a = a;
    }

    public T get() {
        return a;
    }
}
