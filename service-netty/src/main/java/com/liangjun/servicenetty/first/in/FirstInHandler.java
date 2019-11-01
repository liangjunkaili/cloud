package com.liangjun.servicenetty.first.in;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

//@ChannelHandler.Sharable
public class FirstInHandler extends ChannelInboundHandlerAdapter {
    public FirstInHandler() {
        super();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelRegistered...");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelUnregistered...");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive...");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelInactive...");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead...");
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("msg type:"+(buf.hasArray()?"堆内存":"直接内存"));
        int len = buf.readableBytes();
        byte[] arr = new byte[len];
        buf.getBytes(0,arr);
        System.out.println("server received:"+ new String(arr,"utf-8"));
        System.out.println("写回前，msg.refCnt:"+buf.refCnt());
        ChannelFuture future = ctx.writeAndFlush(msg);
        future.addListener((ChannelFuture cf) ->{
            System.out.println("写回后,msg.refCnt:"+buf.refCnt());
        });
//        super.channelRead(ctx, msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete...");
        super.channelReadComplete(ctx);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded...");
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved...");
        super.handlerRemoved(ctx);
    }
}
