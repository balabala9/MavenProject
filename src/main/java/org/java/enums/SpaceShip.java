package org.java.enums;

public enum SpaceShip {
    SCOUT, CARGD, TRANSPORT, CRUISER;

    public static void main(String[] args) {
        for (SpaceShip s : SpaceShip.values()) {
            System.out.println(s);
        }
    }

    @Override
    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }
}
