package org.bean;

import java.io.Serializable;

public class Foo implements Serializable{
    public static int w = 1;
    public static transient int x = 2;
    public int y = 3;
    public transient int z = 4;
}
