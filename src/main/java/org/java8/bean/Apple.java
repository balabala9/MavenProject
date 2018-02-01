package org.java8.bean;

public class Apple {
    private String name;
    private Integer num;

    public Apple(){}
    public Apple(String name,Integer num){
        this.name=name;
        this.num=num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public static int compareByNum(Apple a,Apple b){
       return a.getNum().compareTo(b.getNum());
    }
}
