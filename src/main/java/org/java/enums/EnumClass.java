package org.java.enums;


enum Shrubbery {GROUND, CRAWLING, HANGING}

public class EnumClass {
    //values()方法返回enum实例数组
    public static void main(String[] args) {
        for (Shrubbery s : Shrubbery.values()) {
            //返回enum实例的声明次序，从0开始
            System.out.println("ordinal" + s.ordinal());
            //enum实例声明的名称与toString功能一样
            System.out.println("name" + s.name());
            //比较enum实例的ordinal
            System.out.println("compareTo" + s.compareTo(Shrubbery.CRAWLING));
            //enum实例名称 equal和==功能相同
            System.out.println("equal" + s.equals(Shrubbery.CRAWLING));
            System.out.println(s == Shrubbery.CRAWLING);

            System.out.println("DeclaringClass" + s.getDeclaringClass());
            System.out.println("class" + s.getClass());

            System.out.println("toString " + s.toString());
        }


        Shrubbery s2 = Enum.valueOf(Shrubbery.class, "GROUND");
        System.out.println("valueOf 根据名称返回enum实例声明的名称" + s2);
    }

}
