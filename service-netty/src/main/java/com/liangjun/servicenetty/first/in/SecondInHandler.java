package com.liangjun.servicenetty.first.in;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SecondInHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        Integer integer = (Integer) msg;
//        System.out.println("打印一个整数："+integer);
        String s = (String) msg;
        System.out.println("打印一个字符串："+s);
    }
}
