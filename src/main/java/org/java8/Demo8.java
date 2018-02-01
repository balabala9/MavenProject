package org.java8;

import org.java8.demo6.StringFunc;


import java.util.function.IntFunction;
import java.util.function.LongFunction;

public class Demo8 {
    public static void main(String[] args) {

        //Todo 其他类型数组构造有问题啊
        //数组构造方法引用 TypeName[]::new
        IntFunction<int[]> arrMaker=int[] ::new;
        int[] arr=arrMaker.apply(5);


    }
}
