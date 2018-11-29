package org.java.enums;

public class TypeOfFood {
    public static void main(String[] args) {
        Food food = Food.Appetizer.SALAD;
        System.out.println(((Food.Appetizer) food).name());

    }
}
