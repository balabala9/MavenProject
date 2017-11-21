package org.numberFormat;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class BigDecimalBase {
    public static void main(String[] args) {

        /**
         * BigDecimal
         * 1.java.math中的类
         *
         * 作用:用来对超过16位有效位的数进行精确的运算。（double可以处理16位有效数）
         *
         *
         * 使用方法:BigDecimal所创建的是对象，我们不能使用传统的+、-、*、/等算术运算符直接对其对象进行数学运算，
         * 而必须调用其相对应的方法。方法中的参数也必须是BigDecimal的对象。构造器是类的特殊方法，专门用来创建对象，特别是带有参数的对象。
         */

        //构造器描述
        BigDecimal bigDecimalInt=new BigDecimal(50);//所指定整数值的对象
        BigDecimal bigDecimalInt2=new BigDecimal(20);//所指定整数值的对象
        BigDecimal bigDecimalDouble=new BigDecimal(50.522);//指定双精度值的对象
        BigDecimal bigDecimalLong=new BigDecimal(888888);//长整数值的对象
        BigDecimal bigDecimalString=new BigDecimal("12.55");//以字符串表示的数值的对象


        System.out.println("bigDecimalInt:"+bigDecimalInt);
        //bigDecimalDouble:50.52199999999999846522769075818359851837158203125
        /***
         * 注意
         *
         *  JDK的描述：1、参数类型为double的构造方法的结果有一定的不可预知性。有人可能认为在Java中写入newBigDecimal(0.1)所创建的BigDecimal正好等于 0.1（非标度值 1，其标度为 1），但是它实际上等于0.1000000000000000055511151231257827021181583404541015625。这是因为0.1无法准确地表示为 double（或者说对于该情况，不能表示为任何有限长度的二进制小数）。这样，传入到构造方法的值不会正好等于 0.1（虽然表面上等于该值）。
         2、另一方面，String 构造方法是完全可预知的：写入 newBigDecimal("0.1") 将创建一个 BigDecimal，它正好等于预期的 0.1。因此，比较而言，通常建议优先使用String构造方法。
         3、当double必须用作BigDecimal的源时，请注意，此构造方法提供了一个准确转换；它不提供与以下操作相同的结果：先使用Double.toString(double)方法，然后使用BigDecimal(String)构造方法，将double转换为String。要获取该结果，请使用static valueOf(double)方法。
         */
        System.out.println("bigDecimalDouble:"+bigDecimalDouble);
        System.out.println("bigDecimalLong:"+bigDecimalLong);
        System.out.println("bigDecimalString:"+bigDecimalString);


        /**
         * 输出结果
         *
         * bigDecimalInt:50
         bigDecimalDouble:50.5
         bigDecimalLong:888888
         bigDecimalString:12.55
         */


        /***
         * 方法
         */

        System.out.println(bigDecimalInt.add(bigDecimalInt2));//BigDecimal对象中的值相加，然后返回这个对象
        System.out.println(bigDecimalInt.subtract(bigDecimalInt2));// BigDecimal对象中的值相减，然后返回这个对象
        System.out.println(bigDecimalInt.multiply(bigDecimalInt2));//BigDecimal对象中的值相乘，然后返回这个对象
        System.out.println(bigDecimalInt.divide(bigDecimalInt2));//BigDecimal对象中的值相除，然后返回这个对象



        System.out.println(bigDecimalInt.divide(bigDecimalInt2).toString());//将BigDecimal对象的数值转换成字符串
        System.out.println(bigDecimalInt.divide(bigDecimalInt2).doubleValue());//将BigDecimal对象中的值以双精度数返回
        System.out.println(bigDecimalInt.divide(bigDecimalInt2).floatValue());// 将BigDecimal对象中的值以单精度数返回
        System.out.println(bigDecimalInt.divide(bigDecimalInt2).longValue());//将BigDecimal对象中的值以长整数返回
        System.out.println(bigDecimalInt.divide(bigDecimalInt2).intValue());//将BigDecimal对象中的值以整数返回。


        /***
         * 格式化及例子
         */
        NumberFormat numberFormatCur=NumberFormat.getCurrencyInstance();
        NumberFormat numberFormatPer=NumberFormat.getPercentInstance();
        numberFormatPer.setMaximumFractionDigits(3);


        BigDecimal loanAmount= new BigDecimal("15000.48");
        BigDecimal rate=new BigDecimal("0.008");
        BigDecimal interest=loanAmount.multiply(rate);//利息

        System.out.println("贷款金额:"+numberFormatCur.format(loanAmount));
        System.out.println("利率："+numberFormatPer.format(rate));


        /**
         * 比较
         *
         * 打印结果是：-1、0、1，即左边比右边数大，返回1，相等返回0，比右边小返回-1。
         注意不能使用equals方法来比较大小。
         */

        BigDecimal a = new BigDecimal("1");
        BigDecimal b = new BigDecimal("2");
        BigDecimal c = new BigDecimal("1");
        int result1 = a.compareTo(b);
        int result2 = a.compareTo(c);
        int result3 = b.compareTo(a);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);


        /**
         * BigDecimal.setScale 处理java小数
         */

        System.out.println("----------------------------------------");
        BigDecimal bigDecimal=new BigDecimal("2.325");
        //注意对BigDecimal的值进位时需指定进位方式，否则会发生java.lang.ArithmeticException: Rounding necessary。
//        System.out.println(bigDecimal.setScale(1));//表示保留一位小数，默认用四舍五入方式
        System.out.println(bigDecimal.setScale(1,BigDecimal.ROUND_DOWN));//直接删除多余的小数位，如2.35会变成2.3
        System.out.println(bigDecimal.setScale(1,BigDecimal.ROUND_UP));//进位处理
        System.out.println(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP));//四舍五入
        System.out.println(bigDecimal.setScale(1,BigDecimal.ROUND_HALF_DOWN));//四舍五入


        //表示形式不带指数字段
        BigDecimal bigDecimal1=new BigDecimal("");
        System.out.println(bigDecimal1.toPlainString());


    }
}
