package com.liangjun.servicenetty.http;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName HttpClient
 * @Description TODO
 * @Author junliang
 * @Date 2019/11/23 9:56 AM
 * @Version 1.0
 **/
public class HttpClient {
    private Bootstrap bootstrap = new Bootstrap();
    private EventLoopGroup group = new NioEventLoopGroup();
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    public void init(){
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline pipeline = socketChannel.pipeline();
//                        pipeline.addLast(new LoggingHandler());
                        pipeline.addLast(new HttpClientCodec());
                        pipeline.addLast(new HttpObjectAggregator(65536));
//                        pipeline.addLast(new HttpContentDecompressor());
                        pipeline.addLast(new HttpReponseHandler());
                    }
                });

        for (int i=0;i<10;i++){
            try {
                ChannelFuture future = bootstrap.connect("127.0.0.1",8989).sync();
                future.channel().closeFuture().addListener((r) -> {
                    System.out.println("---------");
                    atomicInteger.incrementAndGet();
//                    group.shutdownGracefully();
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (atomicInteger.get()>=10){
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new HttpClient().init();
    }
}
