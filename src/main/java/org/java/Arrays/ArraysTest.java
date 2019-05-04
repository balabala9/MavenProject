package org.java.Arrays;

import java.util.Arrays;

public class ArraysTest {
    public static void arrayscopyOf() {
        int[] var = {1, 2, 3, 4};
        int[] newVar = Arrays.copyOf(var, 10);
        for (int v : newVar) {
            System.out.print(v + " ");
        }
    }

    public static void systemArraycopy() {
        int[] var = {1, 2, 3, 4};
        int[] newVar = new int[10];
        System.arraycopy(var, 0, newVar, 0, 2);
        for (int v : newVar) {
            System.out.print(v + " ");
        }
    }

    public static void main(String[] args) {
        ArraysTest.systemArraycopy();
    }
}
