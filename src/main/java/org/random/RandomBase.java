package org.random;

import java.util.Random;

public class RandomBase {

    public static void main(String[] args) {
        /**
         * 一.java.lang.Math.Random
         * 调用这个Math.Random()函数能够返回带正号的double值，该值大于等于0.0且小于1.0，即取值范围是[0.0,1.0)的左闭右开区间，返回值是一个伪随机选择的数，在该范围内（近似）均匀分布
         */

        //返回区间[0,3)
        for(int i=0;i<10;i++){
            int num=(int)(Math.random()*3);
            System.out.println(num);
        }


        /**
         * 二.java.util.Random;
         *
         * 1、java.util.Random类中实现的随机算法是伪随机，也就是有规则的随机，所谓有规则的就是在给定种(seed)的区间内随机生成数字；
         * 2、相同种子数的Random对象，相同次数生成的随机数字是完全相同的；（重点）
         * 3、Random类中各方法生成的随机数字都是均匀分布的，也就是说区间内部的数字生成的几率均等
         */


        /**
         * 规则2验证----相同种子数的Random对象，相同次数生成的随机数字是完全相同的
         */

        Random random=new Random(2);
        Random random1=new Random(2);
        for(int i=0;i<4;i++){

            System.out.println("random："+random.nextInt(30));
            System.out.println("random1："+random1.nextInt(30));
        }


        /**
         * 结果：
         * 28
         random：28
         random1：28
         random：12
         random1：12
         random：20
         random1：20
         random：7
         random1：7
         */


        /**
         * 下面Random()的两种构造方法
         1.Random()：创建一个新的随机数生成器。
         2.Random(long seed)：使用单个 long 种子创建一个新的随机数生成器。


         我们可以在构造Random对象的时候指定种子（这里指定种子有何作用，请接着往下看），如：
         Random r1 = new Random(20);
         或者默认当前系统时间对应的相对时间有关的数字作为种子数:
         Random r1 = new Random();
         需要说明的是：你在创建一个Random对象的时候可以给定任意一个合法的种子数，种子数只是随机算法的起源数字，和生成的随机数的区间没有任何关系。如下面的Java代码：
         Random rand =new Random(25);
         int i;
         i=rand.nextInt(100);
         初始化时25并没有起直接作用（注意：不是没有起作用）,rand.nextInt(100);中的100是随机数的上限,产生的随机数为0-100的整数,不包括100。
         */


        Random random2=new Random();
        System.out.println(random2.nextInt());//返回下一个伪随机数，它是此随机数生成器的序列中均匀分布的 int 值。
        System.out.println(random2.nextInt(2));//返回一个伪随机数，它是取自此随机数生成器序列的、在（包括和指定值（不包括）之间均匀分布的int值。
        System.out.println(random2.nextLong());//返回下一个伪随机数，它是取自此随机数生成器序列的均匀分布的 long 值。
        System.out.println(random2.nextFloat());//返回下一个伪随机数，它是取自此随机数生成器序列的、在0.0和1.0之间均匀分布float值。
        System.out.println(random2.nextDouble());//返回下一个伪随机数，它是取自此随机数生成器序列的、在0.0和1.0之间均匀分布的 double值。
        System.out.println(random2.nextBoolean());//返回下一个伪随机数，它是取自此随机数生成器序列的均匀分布的boolean值。
        /**
         * protected int next(int bits)：生成下一个伪随机数。
         * void nextBytes(byte[] bytes)：生成随机字节并将其置于用户提供的 byte 数组中。
         * void setSeed(long seed)：使用单个 long 种子设置此随机数生成器的种子
         */


        /**
         *
         * 方法摘要也就这些，下面给几个例子：
         1.生成[0,1.0)区间的小数：double d1 = r.nextDouble();
         2.生成[0,5.0)区间的小数：double d2 = r.nextDouble() * 5;
         3.生成[1,2.5)区间的小数：double d3 = r.nextDouble() * 1.5 + 1;
         4.生成-231到231-1之间的整数：int n = r.nextInt();
         5.生成[0,10)区间的整数：
         int n2 = r.nextInt(10);//方法一
         n2 = Math.abs(r.nextInt() % 10);//方法二
         */


        /**
         * 最后再来简单对比一下这两个随机函数到底的特点：
         1.java.Math.Random()实际是在内部调用java.util.Random()的,它有一个致命的弱点，它和系统时间有关，也就是说相隔时间很短的两个random比如:
         double a = Math.random()；
         double b = Math.random();
         即有可能会得到两个一模一样的double。
         2.java.util.Random()在调用的时候可以实现和java.Math.Random()一样的功能，而且他具有很多的调用方法，相对来说比较灵活。所以从总体来看，使用java.util.Random()会相对来说比较灵活一些。
         */
    }
}
