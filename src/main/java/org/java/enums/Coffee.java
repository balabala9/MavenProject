package org.java.enums;

public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

    public class Mocha extends Coffee {
    }

    public class Latte extends Coffee {
    }

    public class Cappuccino extends Coffee {
    }

}
