package org.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;


import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


public class Server {

    private void startInbound(int port) {
        EventLoopGroup boosGroup=new NioEventLoopGroup();
        EventLoopGroup wokerGroup=new NioEventLoopGroup();

        ServerBootstrap b=new ServerBootstrap();
        try{
            b.group(boosGroup,wokerGroup)
                    .channel(NioServerSocketChannel.class)
                    //此套接口排队的最大连接个数（backLog参数）
                    .childOption(ChannelOption.SO_BACKLOG,100)
                    //接收缓冲区和发送缓冲区的大小
                    .childOption(ChannelOption.SO_RCVBUF,1024)
                    .childOption(ChannelOption.SO_SNDBUF,1024)
                    //长时间没有数据通信时，tcp会自动发送一个活动探测数据报文
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //nagle算法小数据积累到一定大小在发送
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    //选项用来设置延迟关闭的时间,等待套接字发送缓冲区中的数据发送完成
                    .childOption(ChannelOption.SO_LINGER,0)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            socketChannel.pipeline().addLast(new HttpRequestHandler());
                        }
                    });
            // 绑定端口，开始接收进来的连接
            ChannelFuture f = b.bind(port).sync();
            // 等待服务器 socket 关闭 。
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            wokerGroup.shutdownGracefully();
            boosGroup.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws InterruptedException {
        new Server().startInbound(8080);
    }
}
