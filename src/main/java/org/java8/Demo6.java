package org.java8;

import org.java8.demo6.MyStringOps;
import org.java8.demo6.StringFunc;

public class Demo6 {

    public static String stringOP(StringFunc sf, String str){
        return sf.func(str);
    }
    public static void main(String[] args) {
        String str="Alice,Good afternoon";

        /**
         * 静态方法的引用
         * ClassName::staticMethodName
         */
//        String strr= Demo6.stringOP(MyStringOps::strReverse,str);


        /**
        * 特定实例对象的方法引用
         *
         * instanceReference::methodName
        * */
        MyStringOps ops=new MyStringOps();
        String strr=Demo6.stringOP(ops::strReverse1,str);
        System.out.println(strr);
    }
}
