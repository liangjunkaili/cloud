package com.liangjun.servicenetty.first.im;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class HeartBeatServerHandler extends IdleStateHandler {
    private static final int READ_IDLE_GAP = 150;//最大空闲时间，单位s
    public HeartBeatServerHandler(){
        super(READ_IDLE_GAP,0,0, TimeUnit.SECONDS);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg==null){
            super.channelRead(ctx, msg);
            return;
        }
        //将数据包发送给客户端
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READ_IDLE_GAP + "秒内未读到数据，关闭连接！");
        //关闭操作
    }
}
