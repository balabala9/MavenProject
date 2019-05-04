package org.java.enums;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;

public class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private static Random rand = new Random(47);
    private Class[] types = {Coffee.Latte.class, Coffee.Mocha.class};
    private int size = 0;

    public CoffeeGenerator() {
    }

    public CoffeeGenerator(int sz) {
        size = sz;
    }

    @NotNull
    @Override
    public Iterator<Coffee> iterator() {
        return null;
    }

    @Override
    public Spliterator<Coffee> spliterator() {
        return null;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
