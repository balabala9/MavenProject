package org.java8;

import com.alibaba.fastjson.JSON;
import org.java8.bean.Apple;
import java.util.Arrays;


public class Demo5 {
    public static void main(String[] args) {


        Apple[] arr = new Apple[]{
                new Apple("Alice", 8),
                new Apple("tom", 6)
        };

//        Arrays.sort(arr, new Comparator<Apple>() {
//            @Override
//            public int compare(Apple o1, Apple o2) {
//                return o1.getNum().compareTo(o2.getNum());
//            }
//        });


        //使用lambda表达式
//        Arrays.sort(arr,(x,y)->x.getNum().compareTo(y.getNum()));

        //类的静态方法
//        Arrays.sort(arr,(x,y)->Apple.compareByNum(x,y));

        //Lambda表达式调用了一个已存在的方法,使用方法引用来替代这个Lambda表达式
        Arrays.sort(arr, Apple::compareByNum);

        System.out.println(JSON.toJSONString(arr));


    }
}
