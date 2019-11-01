package com.liangjun.servicenetty.first;

import com.liangjun.servicenetty.first.in.decode.MyDecoder;
import com.liangjun.servicenetty.first.in.SecondInHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class FirstNettyServer {
    private ServerBootstrap bootstrap = new ServerBootstrap();
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();
    public void init(){
        try {
            bootstrap.group(boss,worker);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.localAddress(new InetSocketAddress(8989));
            //底层TCP协议得心跳机制，true为连接保持心跳，间隔是7200s，默认为false
    //        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new MyDecoder());
    //                pipeline.addLast(new FirstInHandler());
                    pipeline.addLast(new SecondInHandler());
    //                pipeline.addLast(new FirstOutHandler());
    //                pipeline.addLast(new SecondOutHandler());
                }
            });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("服务器启动成功，监听端口："+future.channel().localAddress());
            ChannelFuture closeFuture = future.channel().closeFuture();
            closeFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new FirstNettyServer().init();
    }
}
