package org.java.enums;

public enum OzWitch {
    //enum实例
    WEST("Miss Gulch,aka"),
    EAST("Wicked");
    //向enum添加方法
    private String description;

    private OzWitch(String description) {
        this.description = description;
    }

    //向enum中添加方法
    public static void main(String[] args) {
        for (OzWitch ozWitch : OzWitch.values()) {
            System.out.println(ozWitch.description);
        }
    }
}
