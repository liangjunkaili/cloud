package com.liangjun.servicenetty.first.out.encode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncoder extends MessageToByteEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(integer);
        System.out.println("encoder Integer = " +integer);
    }
}
