package org.io.javaIO;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class JavaNIOBase {

    public JavaNIOBase() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        /**
         * NIO主要用到的是块，所以NIO的效率要比IO高很多
         * Buffer和Channel是标准NIO中的核心对象（网络NIO中还有个Selector核心对象)
         *
         *
         *
         *
         * Channel是对原IO中流的模拟，任何来源和目的数据都必须通过一个Channel对象。一个Buffer实质上是一个容器对象，发给Channel的所有对象都必须先放到Buffer中；同样的，从Channel中读取的任何数据都要读到Buffer中。
         */

        /**
         * 关于Buffer
         * Buffer是一个对象，它包含一些要写入或读出的数据
         * 在NIO中，数据是放入buffer对象的，而在IO中，数据是直接写入或者读到Stream对象的。
         * 应用程序不能直接对 Channel 进行读写操作，而必须通过 Buffer 来进行，即 Channel 是通过 Buffer 来读写数据的。
         *
         *
         *
         * 是NIO读写数据的中转池
         * Buffer实质上是一个数组，通常是一个字节数据，但也可以是其他类型的数组。
         * 提供了对数据的结构化访问，而且还可以跟踪系统的读写进程。

         使用 Buffer 读写数据一般遵循以下四个步骤：

         写入数据到 Buffer；
         调用 flip() 方法；
         从 Buffer 中读取数据；
         调用 clear() 方法或者 compact() 方法。

         当向 Buffer 写入数据时，Buffer 会记录下写了多少数据。一旦要读取数据，需要通过 flip() 方法将 Buffer 从写模式切换到读模式。在读模式下，可以读取之前写入到 Buffer 的所有数据。
         一旦读完了所有的数据，就需要清空缓冲区，让它可以再次被写入。有两种方式能清空缓冲区：调用 clear() 或 compact() 方法。clear() 方法会清空整个缓冲区。compact() 方法只会清除已经读过的数据。任何未读的数据都被移到缓冲区的起始处，新写入的数据将放到缓冲区未读数据的后面。


         *
         */

        /***
         * Buffer是被用来当缓冲区使用的小部分内存，有七个子类ByteBuffer, CharBuffer, DoubleBuffer, FloatBuffer, IntBuffer, LongBuffer, ShortBuffer ，分别用来存储不同的数据类型。
         *

         mark:备忘位置. mark()存储当前position的值，reset()设定position的值为之前标记的值。默认值0

         position：下一个要读/写的数组的元素的下标索引。默认值0

         limit：第一个不能读/写的数组的元素的下标索引。初始化状态=capacity值

         capacity：指的是缓冲区能够容纳元素的最大数量，这个值在缓冲区创建时被设定，而且不能够改变，
         */


        /**
         *1.创建buffer对象
         * 是抽象类，但是它们都有一个用于创建相应实例的静态工厂方法
         *
         */

        ByteBuffer byteBuffer=ByteBuffer.allocate(50);
        System.out.println("初始化状态buffer各参数值");

        System.out.println("limit:"+byteBuffer.limit());
        System.out.println("position:"+byteBuffer.position());
        System.out.println("capacity:"+byteBuffer.capacity());



        System.out.println("调用mark()方法:"+byteBuffer.mark());
        System.out.println("position:"+byteBuffer.position());

        /**
         * 总结：
         *初始化
         * mark=position=0
         * limit=capacity
         */


        /**
         * 向buffer写数据
         * 1.从channel写到buffer
         * 2.通过put方法
         */

        byteBuffer.put("你好".getBytes());


        System.out.println("写入数据状态buffer各参数值");
        System.out.println("limit:"+byteBuffer.limit());
        System.out.println("position:"+byteBuffer.position());
        System.out.println("capacity:"+byteBuffer.capacity());

//        System.out.println("调用reset()方法:"+byteBuffer.reset());
//        System.out.println("position:"+byteBuffer.position());

        //rewind() 数据重写前调用  position设为0,limit不会
//        byteBuffer.rewind();
//        System.out.println("重写数据状态buffer各参数值");
//        System.out.println("limit:"+byteBuffer.limit());
//        System.out.println("position:"+byteBuffer.position());
//        System.out.println("capacity:"+byteBuffer.capacity());

        //flip()读数据前调用 limit设置为当前position，position设为0

        byteBuffer.flip();
        System.out.println("读数据状态buffer各参数值");
        System.out.println("limit:"+byteBuffer.limit());
        System.out.println("position:"+byteBuffer.position());
        System.out.println("capacity:"+byteBuffer.capacity());

        //clear()一般写数据前调用 把position设为0 limit设为capacity,
//        byteBuffer.clear();
//
//        System.out.println("clear()状态buffer各参数值");
//        System.out.println("limit:"+byteBuffer.limit());
//        System.out.println("position:"+byteBuffer.position());
//        System.out.println("capacity:"+byteBuffer.capacity());

        //总结：一个汉字占3个字节

        //读数据
        byte[] bytes=new byte[6];
        while (byteBuffer.hasRemaining()){
          byteBuffer.get(bytes,0,byteBuffer.limit());

        }
        byteBuffer.clear();
        System.out.println(new String(bytes));


        /**
         * channel
         * 既可以从通道中读取数据，又可以写数据到通道。但流的读写通常是单向的。
         通道可以异步地读写。
         通道中的数据总是要先读到一个Buffer，或者总是要从一个Buffer中写入。
         */

        /**
         *
         * 通道类型
         FileChannel//从文件中读写数据  阻塞模式
         DatagramChannel//能通过UDP读写网络中的数据。
         SocketChannel//能通过TCP读写网络中的数据。
         ServerSocketChannel//可以监听新进来的TCP连接，像Web服务器那样。对每一个新进来的连接都会创建一个SocketChannel。
         */


        /**
         * FileChannel
         */


        /**
         * 打开FileChannel
         * 使用一个InputStream、OutputStream或RandomAccessFile来获取一个FileChannel实例
         */

        FileOutputStream fileOutputStream=new FileOutputStream("k.txt");
        FileChannel outFileChannel=fileOutputStream.getChannel();


        /**
         * 向Channel写数据
         */

        ByteBuffer fileBuffer1=ByteBuffer.allocate(8);
        fileBuffer1.put("拉拉".getBytes());
        fileBuffer1.flip();

        while (fileBuffer1.hasRemaining()){
            outFileChannel.write(fileBuffer1);
        }

        outFileChannel.close();

        FileInputStream fileInputStream=new FileInputStream("k.txt");
        FileChannel inFileChannel=fileInputStream.getChannel();

        /**
         * 从Channel读数据
         */

        ByteBuffer fileBuffer = ByteBuffer.allocate(6);
        int bytesRead = inFileChannel.read(fileBuffer);//read 返回int值表示有多少个字节被读到buffer,-1：表示文件末尾
        byte[] bytes1=new byte[6];
        fileBuffer.flip();
        while (fileBuffer.hasRemaining()){
            fileBuffer.get(bytes1,0,fileBuffer.limit());
        }
        System.out.println(new String(bytes1));
        inFileChannel.close();


        /**
         * socketChannel 是一个连接到TCP网络套接字的通道
         * 1.打开一个SocketChannel并连接到互联网上的某台服务器
         * 2.一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。
         */

        /**
         * 打开socketChannel
         * 注意：关闭socketChannel
         */

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("http://jenkov.com", 80));


        //socketChannel 非阻塞模式,connect,read,write
        socketChannel.configureBlocking(false);

        //如果SocketChannel在非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了。为了确定连接是否建立，可以调用finishConnect()的方法
        while (!socketChannel.finishConnect()){

        }


        /**
         * write()

         非阻塞模式下，write()方法在尚未写出任何内容时可能就返回了。所以需要在循环中调用write()。

         read()

         非阻塞模式下,read()方法在尚未读取到任何数据时可能就返回了。所以需要关注它的int返回值，它会告诉你读取了多少字节。

         非阻塞模式与选择器

         非阻塞模式与选择器搭配会工作的更好，通过将一或多个SocketChannel注册到Selector，可以询问选择器哪个通道已经准备好了读取，写入等。
         */


        /**
         * serverSocketChannel
         * 一个可以监听新进来的TCP连接的通道
         * 注意：关闭通道
         */

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        while(true){
            SocketChannel socketChannel1 =
                    serverSocketChannel.accept();//当 accept()方法返回的时候,它返回一个包含新进来的连接的 SocketChannel。因此, accept()方法会一直阻塞到有新连接到达。

            //do something with socketChannel...
        }

        /**
         * 非阻塞模式
         * ServerSocketChannel可以设置成非阻塞模式。在非阻塞模式下，accept() 方法会立刻返回，如果还没有新进来的连接,返回的将是null。 因此，需要检查返回的SocketChannel是否是
         */

//        while(true){
//            SocketChannel socketChannel1 =
//                    serverSocketChannel.accept();//当 accept()方法返回的时候,它返回一个包含新进来的连接的 SocketChannel。因此, accept()方法会一直阻塞到有新连接到达。
//
//            if(socketChannel != null){
//                //do something with socketChannel...
//            }
//
//        }


        /**
         * selector
         * selector 就是您注册对各种 I/O 事件兴趣的地方，而且当那些事件发生时，就是这个对象告诉您所发生的事件。
         * Selector是一个对象，它可以注册很多个Channel，监听各个Channel上发生的事件,并且能够根据事件情况决定Channel读写。这样，通过一个线程管理多个Channel，就可以处理大量网络连接了
         */




    }


    public void selectorTest() throws IOException {
        SocketChannel channel=SocketChannel.open();
        //创建selector
        Selector selector=Selector.open();
        //注册Channel到Selector
        //需要把Channel注册到Selector上。通过调用 channel.register（）方法来实现注册
        //注意:注册的Channel 必须设置成异步模式 才可以,，否则异步IO就无法工作，这就意味着我们不能把一个FileChannel注册到Selector，因为FileChannel没有异步模式，但是网络编程中的SocketChannel是可以的。
        channel.configureBlocking(false);
        // SelectionKey 代表这个通道在此 Selector 上的这个注册。当某个 Selector 通知您某个传入事件时，它是通过提供对应于该事件的 SelectionKey 来进行的
        SelectionKey key =channel.register(selector, SelectionKey.OP_CONNECT);
        /**
         *
         */





    }

    /**
     * register()方法的第二个参数，它是一个“interest set”,意思是注册的Selector对Channel中的哪些事件感兴趣，事件类型有四种
     *
     *
     * 通道触发了一个事件意思是该事件已经 Ready(就绪)
     Connect:某个Channel成功连接到另一个服务器称为 Connect Ready
     Accept:一个ServerSocketChannel准备好接收新连接称为 Accept Ready
     Read:一个有数据可读的通道可以说是 Read Ready
     Write:等待写数据的通道可以说是Write Ready


     1. SelectionKey.OP_CONNECT
     2. SelectionKey.OP_ACCEPT
     3. SelectionKey.OP_READ
     4. SelectionKey.OP_WRITE

     */



}
