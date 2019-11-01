package com.liangjun.servicenetty.first.im;

import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoop;
import io.netty.util.concurrent.GenericFutureListener;


public class CommandController {

    GenericFutureListener<ChannelFuture> listener = (ChannelFuture f) -> {
      final EventLoop eventLoop = f.channel().eventLoop();
      if (!f.isSuccess()){
          System.out.println("连接失败！在10s之后准备尝试重连！");
      }
    };
}
