package com.liangjun.servicenetty.first.in.decode;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

public class IntegerAddDecoder extends ReplayingDecoder<IntegerAddDecoder.Status> {
    enum Status{
        PARSE_1,PARSE_2
    }
    private int first;
    private int second;
    public IntegerAddDecoder(){
        super(Status.PARSE_1);
    }
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        switch (state()){
            case PARSE_1:
                first = byteBuf.readInt();
                checkpoint(Status.PARSE_2);
                break;
            case PARSE_2:
                second = byteBuf.readInt();
                Integer sum = first + second;
                list.add(sum);
                checkpoint(Status.PARSE_1);
                break;
            default:
                break;
        }
    }
}
