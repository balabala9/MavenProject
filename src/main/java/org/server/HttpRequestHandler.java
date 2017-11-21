package org.server;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class HttpRequestHandler extends SimpleChannelInboundHandler {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

        System.out.println(JSON.toJSONString(o));
//        ByteBuf result = (ByteBuf) o;
//        byte[] result1 = new byte[result.readableBytes()];
//        result.readBytes(result1);
//        String resultStr = new String(result1);
//        System.out.println(resultStr);

    }
}
