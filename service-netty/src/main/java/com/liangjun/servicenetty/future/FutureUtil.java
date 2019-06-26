package com.liangjun.servicenetty.future;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.*;

public class FutureUtil {
    public static void main(String[] args) {
        ChannelFuture channelFuture;
        NioEventLoop loop;
        ChannelPipeline pipeline;
        ChannelHandler handler;
        ChannelHandlerAdapter adapter;
        ByteToMessageDecoder decoder;
        MessageToMessageDecoder toMessageDecoder;
        LengthFieldBasedFrameDecoder lengthFieldBasedFrameDecoder;
        MessageToByteEncoder messageToByteEncoder;
        MessageToMessageEncoder messageToMessageEncoder;
        LengthFieldPrepender prepender;
        ChannelHandlerContext context;
        ServerBootstrap serverBootstrap;
        Bootstrap bootstrap;
        Channel channel;
        NioServerSocketChannel serverSocketChannel;
        NioSocketChannel socketChannel;
    }
}
