package org.String;

public class StringBase {


    public static void main(String[] args) {
        //null 值

        /**
         * null是关键字，像public、static、final。它是大小写敏感的，你不能将null写成Null或NULL，编译器将不能识别它们然后报错
         */


        /**
         * null是任何引用类型的默认值(这对所有变量都是适用的，如成员变量、局部变量、实例变量、静态变量（但当你使用一个没有初始化的局部变量，编译器会警告你）)
         */
//        String str;
//        System.out.println(str);

        /**
         * null既不是对象也不是一种类型，它仅是一种特殊的值，你可以将其赋予任何引用类型，你也可以将null转化成任何类型
         */
        String str1=null;
        System.out.println(str1);

        Integer str2=(Integer)null;
        System.out.println(str2);

        /**
         * null可以赋值给引用变量，你不能将null赋给基本类型变量，例如int、double、float、boolean。编译器将会报错。
         */
//        int a=null;

        /**
         * 任何含有null值的包装类在Java拆箱生成基本数据类型时候都会抛出一个空指针异常
         */

//        Integer iAmNull1 = null;
//        int i = iAmNull1; // Remember - No Compilation Error


        /**
         * 如果使用了带有null值的引用类型变量，instanceof操作将会返回false
         */
//
//        Integer iAmNull = null;
//        if(iAmNull instanceof Integer){
//            System.out.println("iAmNull is instance of Integer");
//        }else{
//            System.out.println("iAmNull is NOT an instance of Integer");
//        }


        /**
         * 调用非静态方法来使用一个值为null的引用类型变量。它将会抛出空指针异常.使用静态方法来使用一个值为null的引用类型变量。因为静态方法使用静态绑定，不会抛出空指针异常
         */


        StringBase stringBase=null;
//        stringBase.iAmNonStaticMethod();
        stringBase.iAmStaticMethod();


        /**
         * 你可以使用==或者!=操作来比较null值，但是不能使用其他算法或者逻辑操作，例如小于或者大于。在Java中null==null将返回true。
         */


        /**
         *
         */
    }

    private static void iAmStaticMethod(){
        System.out.println("I am static method, can be called by null reference");
    }
    private void iAmNonStaticMethod(){
        System.out.println("I am NON static method, don't date to call me by null");
    }




}
