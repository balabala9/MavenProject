package org.java.string;

import java.util.Arrays;
import java.util.List;

public class SplitTest {
    public static void main(String[] args) {
//        String randomName="222";
//        String fileName="333.png";
//        int len=fileName.split("\\.").length;
//        String res=fileName.split("\\.").length > 0 ? "." + fileName.split("\\.")[1] : "";
//        String currentName = randomName + (fileName.split("\\.").length > 0 ? "." + fileName.split("\\.")[1] : "");

        List<String> list = Arrays.asList("张国祥", "李玉青");
        System.out.println(list.contains("李玉"));
    }
}
