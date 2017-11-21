package org.numberFormat;

import java.text.NumberFormat;

public class NumberFormatBase {

    public static void main(String[] args) {
        //NumberFormat 数字格式化类


        double num=23369.3323232323;
        //返回当前缺省语言环境的缺省数值格式(2)
        NumberFormat numberFormat1=NumberFormat.getInstance();
        numberFormat1.setMaximumIntegerDigits(2);
        System.out.println(numberFormat1.format(num));
       // 返回当前缺省语言环境的通用格式(￥3.00)--------------疑惑
        NumberFormat numberFormat2=NumberFormat.getCurrencyInstance();
        System.out.println(numberFormat2.format(num));
        //返回当前缺省语言环境的通用数值格式（￥3.00） --------疑惑
        NumberFormat numberFormat3=NumberFormat.getNumberInstance();
        System.out.println(numberFormat2.format(num));

        //返回当前缺省语言环境的百分比格式
        NumberFormat numberFormat4=NumberFormat.getPercentInstance();
        System.out.println(numberFormat4.format(num));

        //setMaximumFractionDigits(int) 设置数值的小数部分允许的最大位数。
        //setMaximumIntegerDigits(int)  设置数值的整数部分允许的最大位数。(从整数低位取)
        //setMinimumFractionDigits(int) 设置数值的小数部分允许的最小位数。
        //setMinimumIntegerDigits(int)  设置数值的整数部分允许的最小位数.





    }
}
