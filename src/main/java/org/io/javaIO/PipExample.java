package org.io.javaIO;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class PipExample {
    public static void main(String[] args) throws IOException {
        /**
         * 管道媒介
         * 一个管道既可以作为数据源媒介也可作为目标媒介
         */

        PipedOutputStream pipedOutputStream=new PipedOutputStream();
        PipedInputStream pipedInputStream=new PipedInputStream(pipedOutputStream);
        pipedOutputStream.write("Hello world, pipe!".getBytes());

        byte b[] = new byte[19];
        pipedInputStream.read(b);
        System.out.println(new String(b));

//        final PipedOutputStream output = new PipedOutputStream();
//        final PipedInputStream  input  = new PipedInputStream(output);
//        output.write( "Hello world, pipe!".getBytes());
//        int data = input.read();
//        while( data != -1){
//            System. out.print(( char) data);
//            data = input.read();
//        }
    }
}
