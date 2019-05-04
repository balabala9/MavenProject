package org.algorithm;

import java.io.Serializable;
import java.util.Arrays;

public class MyArrayList<E> implements Serializable {

    private static final Object[] EMPTY_ELEMENTDATA = {};
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private static final int DEFAULT_CAPACITY = 5;
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    transient Object[] elementData;
    private int size;

    public MyArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }

    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0 || minCapacity - Integer.MAX_VALUE > 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
                Integer.MAX_VALUE :
                MAX_ARRAY_SIZE;
    }

    public boolean add(E e) {
        //判断elementData数组元素大小,不够进行增加
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    public E get(int index) {

        rangeCheck(index);
        E e = (E) elementData[index];
        return e;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new OutOfMemoryError(outOfBoundsMsg(index));
        }
    }

    private void ensureCapacityInternal(int minCapacity) {
        ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
    }

    public int calculateCapacity(Object[] elementData, int minCapacity) {
        if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            return Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        return minCapacity;
    }

    public void ensureExplicitCapacity(int minCapacity) {
        if (minCapacity - this.elementData.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = this.elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);

        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);

    }

    public boolean delete(int index) {
        rangeCheck(index);
        for (int i = index; i < this.size; i++) {
            elementData[index] = elementData[i + 1];
        }
        size--;
        return true;
    }

    public int size() {
        return size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }


}
