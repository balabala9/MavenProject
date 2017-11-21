package org.serializable;

import org.bean.Foo;

import java.io.*;

public class SerializableBase {
    /**
     * 序列化：写入字节序列到目标文件
     * 反序列化：读取字节序列进行重构
     */

    public static void main(String[] args) throws IOException {
        Foo foo = new Foo();
        System.out.printf("w: %d%n", Foo.w);
        System.out.printf("x: %d%n", Foo.x);
        System.out.printf("y: %d%n", foo.y);
        System.out.printf("z: %d%n", foo.z);
        try (FileOutputStream fos = new FileOutputStream("x.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(foo);
        }

        foo = null;

        try (FileInputStream fis = new FileInputStream("x.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            System.out.println();
            foo = (Foo) ois.readObject();
            System.out.printf("w: %d%n", Foo.w);
            System.out.printf("x: %d%n", Foo.x);
            System.out.printf("y: %d%n", foo.y);
            System.out.printf("z: %d%n", foo.z);
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
        }
    }
}
