package org.java.bytes;

public class BinaryToDecimals {


    /***
     * 除基取余数
     */
    public static int decimalToBinary(int d) {

        //存放二进制位数
        int i = 0;
        //存放二进制
        int bin = 0;
        // 存放余数
        int r = 0;
        while (d != 0) {
            r = d % 2;
            d = d / 2;
            bin += r * Math.pow(10, i);
            i++;
        }
        return bin;
    }


    public static void main(String[] args) {
        int res = BinaryToDecimals.decimalToBinary(-128);
        System.out.println(res);
    }
}
