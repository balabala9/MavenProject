package org.java.bytes;

import java.nio.ByteBuffer;

public class BytesToInt {
    public static void main(String[] args) {
        byte[] bytes = ByteBuffer.allocate(4).putInt(100).array();
        for (byte b : bytes) {
            System.out.println(b);
        }
    }
}
