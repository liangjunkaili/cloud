package com.liangjun.servicenetty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;
import java.util.Map;

public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest request) throws Exception {
        request.headers().forEach((Map.Entry<String,String> entry) -> {
            System.out.println(entry.getKey()+"="+entry.getValue());
        });
        ByteBuf buf = request.content();
        byte[] bytes = new byte[buf.capacity()];
        buf.readBytes(bytes);
        System.out.println(new String(bytes,"utf-8"));
        String uri = request.uri();
        String msg = "<html><head><title>test</title></head><body>你请求uri为：" + uri+"</body></html>";
        buf.writeBytes(msg.getBytes());
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                Unpooled.copiedBuffer(buf));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/html; charset=UTF-8");
        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
