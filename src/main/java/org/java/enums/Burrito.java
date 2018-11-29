package org.java.enums;


enum Spiciness {
    NOT, MILD, MEDIUM, HOT, FLAMING
}

public class Burrito {
    Spiciness degree;

    public Burrito(Spiciness degree) {
        this.degree = degree;
    }

//    @Override
//    public String toString() {
//        return "Burrito is "+degree;
//    }

    public static void main(String[] args) {
        System.out.println(new Burrito(Spiciness.NOT));
        System.out.println(new Burrito(Spiciness.MEDIUM));

    }
}
