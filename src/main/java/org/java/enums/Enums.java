package org.java.enums;

import java.util.Random;

enum Fruit {
    APPLE, WATER;
}

public class Enums {
    private static Random rand = new Random(47);

    //Class<T> 利用Class对象获取enum实例的数组，<T extends Enum<T>>
    //T是一个enum实例
    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    // T[] 从数组中随机选择一个元素
    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }

    public static void main(String[] args) {

        System.out.println(Enums.random(Fruit.class));

    }
}
