package com.liangjun.servicenetty.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

public class HttpServer {
    public static void main(String[] args) {
        new HttpServer(8989).run();
    }
    public HttpServer(int port){
        this.port = port;
    }
    private int port;
    private ServerBootstrap serverBootstrap = new ServerBootstrap();
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();
    public void run(){
        try {
            serverBootstrap.group(boss,worker);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new HttpServerCodec());
                    pipeline.addLast(new HttpObjectAggregator(65536));
                    pipeline.addLast(new HttpRequestHandler());
                }
            });
            ChannelFuture future = serverBootstrap.bind(port);
            future.addListener((ChannelFuture f) -> {
                System.out.println("server 启动成功，端口为："+f.channel().localAddress());
            });
            ChannelFuture closeFuture = future.channel().closeFuture();
            closeFuture.addListener((ChannelFuture f) -> {
                boss.shutdownGracefully();
                worker.shutdownGracefully();
                System.out.println("链路关闭");
            });
        }catch (Exception e){

        }
    }
}
