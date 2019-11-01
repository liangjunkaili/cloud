package com.liangjun.servicenetty.first.in.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class StringReplayDecoder extends ReplayingDecoder<StringReplayDecoder.Status> {
    enum Status{
        PARSE_1,PARSE_2
    }
    private int length;
    private byte[] inBytes;
    public StringReplayDecoder(){
        super(Status.PARSE_1);
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()){
            case PARSE_1:
                length = byteBuf.readInt();
                inBytes = new byte[length];
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                byteBuf.readBytes(inBytes,0,length);
                list.add(new String(inBytes,"utf-8"));
                checkpoint(Status.PARSE_1);
                break;
            default:
                break;
        }
    }
}
