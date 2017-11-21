package org.numberFormat;

import java.text.DecimalFormat;

public class DecimalFormatBase {

    //float类型 小数精确到7位有效位数,4个字节
    //double类型 小数点精确到16位有效位数 8个字节
    public static void main(String[] args) {

        //float和double
        float a = (float) 1.123456789;
        double d = 1.123456789123456789;
        System.out.println(d);


        /**
         * DecimalFormat 类主要靠 # 和 0 两种占位符号来指定数字长度。0 表示如果位数不足则以 0 填充，# 表示只要有可能就把数字拉上这个位置
         */
        double num = 1234567898.9123456789123456789;
        double num1 = 1234567898.9;
        //取一位整数（有疑惑1234567899）
        DecimalFormat decimalFormat1 = new DecimalFormat("0");
        //取一位整数和两位小数  (结果1234567898.91)
        DecimalFormat decimalFormat2 = new DecimalFormat("0.00");
        //取所有整数部分(1234567899)
        DecimalFormat decimalFormat3 = new DecimalFormat("#");
        //以百分比方式计数，并取两位小数
        DecimalFormat decimalFormat4=new DecimalFormat("#.##%");

        double num3=299792458;
        //显示为科学计数法，并取五位小数
        DecimalFormat decimalFormat5=new DecimalFormat("#.#####E0");

        //每三位以逗号进行分隔。
        DecimalFormat decimalFormat6=new DecimalFormat(",###");

        //将格式嵌入文本
        DecimalFormat decimalFormat7=new DecimalFormat("光速大小为每秒','###米");
        System.out.println(decimalFormat7.format(num3));


    }
}
