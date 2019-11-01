package com.liangjun.servicenetty.first.in.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class StringIntegerHeaderDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//        while (byteBuf.readableBytes()<4){
//            return;
//        }
//        byteBuf.markReaderIndex();
//        int length = byteBuf.readInt();
//        if (byteBuf.readableBytes()<length){
//            byteBuf.resetReaderIndex();
//            return;
//        }
        int length = byteBuf.readableBytes();
        byte[] inBytes = new byte[length];
        byteBuf.readBytes(inBytes,0,length);
        list.add(new String(inBytes,"utf-8"));
    }
}
