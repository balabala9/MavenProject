package org.io.javaIO;

public class JavaIOBase {

    public static void main(String[] args) {

        /**
         * 流
         * 在Java IO中，流是一个核心的概念
         * 流从概念上来说是一个连续的数据流。你既可以从流中读取数据，也可以往流中写数据。
         * 流与数据源或者数据流向的媒介相关联
         * 在Java IO中流既可以是字节流(以字节为单位进行读写)，也可以是字符流(以字符为单位进行读写)
         */


        /**
         *
         * IO相关的媒介
         *
         * Java的IO包主要关注的是从原始数据源的读取以及输出原始数据到目标媒介
         *
         * 以下是最典型的数据源和目标媒介：
         *
         * 文件
         管道
         网络连接
         内存缓存
         System.in, System.out, System.error(注：Java标准输入、输出、错误输出)

         */

        /**
         * 区别流和io媒介的作用
         * 流从概念上来说是一个连续的数据流。你既可以从流中读取数据，也可以往流中写数据
         * 数据源和目标媒介
         */

        /**
         * Java IO的类型
         *
         * 从是读媒介还是写媒介的维度看，Java IO可以分为：
         * 输入流：InputStream和Reader
           输出流：OutputStream和Writer

           从其处理流的类型的维度上看，Java IO又可以分为：
         字节流：InputStream和OutputStream
         字符流：Reader和Writer
         */

        /**
         * 流处理的流程
         * 程序需要通过InputStream或Reader从数据源读取数据，然后用OutputStream或者Writer将数据写入到目标媒介中
         */


        /**
         * io类库
         * 上面我们介绍了Java IO中的四各类：InputStream、OutputStream、Reader、Writer，其实在我们的实际应用中，我们用到的一般是它们的子类，之所以设计这么多子类，目的就是让每一个类都负责不同的功能，以方便我们开发各种应用。各类用途汇总如下：

         文件访问
         网络访问
         内存缓存访问
         线程内部通信(管道)
         缓冲
         过滤
         解析
         读写文本 (Readers / Writers)
         读写基本类型数据 (long, int etc.)
         读写对象

         */


        /**
         * javaIO的基本用法
         *
         *
         * 管道媒介
         * 管道主要用来实现同一个虚拟机中的两个线程进行交流。因此，一个管道既可以作为数据源媒介也可作为目标媒介。

         需要注意的是java中的管道和Unix/Linux中的管道含义并不一样，在Unix/Linux中管道可以作为两个位于不同空间进程通信的媒介，而在java中，管道只能为同一个JVM进程中的不同线程进行通信。和管道相关的IO类为：PipedInputStream和PipedOutputStream
         */
    }
}
