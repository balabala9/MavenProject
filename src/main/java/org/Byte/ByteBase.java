package org.Byte;

import java.io.UnsupportedEncodingException;

public class ByteBase {
    public static void main(String[] args) throws UnsupportedEncodingException {

        /**
         * 负数在计算机中用补码表示
         */


        /**
         *  一、正整数的原码、反码、补码完全一样，即符号位固定为0，数值位相同

         二、负整数的符号位固定为1，由原码变为补码时，规则如下：

         1、原码符号位1不变，整数的每一位二进制数位求反，得到反码

         2、反码符号位1不变，反码数值位最低位加1，得到补码
         */

        /**
         * -126
         *
         */

        System.out.println((byte)130);


        byte num1=-126;
        System.out.println(Integer.toBinaryString(num1));//11111111111111111111111110000010


        /**
         * 字节数组转字符串
         * 直接使用new String()
         */

        String str="拜拜漏";
        byte[] byte1=str.getBytes();
        System.out.println(new String(byte1,"UTF-8"));



    }
}
