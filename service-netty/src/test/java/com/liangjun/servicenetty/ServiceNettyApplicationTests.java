package com.liangjun.servicenetty;

import com.liangjun.servicenetty.first.in.FirstInHandler;
import com.liangjun.servicenetty.first.in.decode.*;
import com.liangjun.servicenetty.first.in.SecondInHandler;
import com.liangjun.servicenetty.first.out.encode.MyEncoder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Random;


//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ServiceNettyApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void testInHandlerLifeCircle() {
        FirstInHandler inHandler = new FirstInHandler();
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
                channel.pipeline().addLast(inHandler);
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeInt(1);
        channel.writeInbound(buffer);
        channel.flush();
        channel.writeInbound(buffer);
        channel.flush();
        channel.close();
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDecoder() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
                channel.pipeline().addLast(new MyDecoder());
//                channel.pipeline().addLast(new IntegerAddDecoder());
//                channel.pipeline().addLast(new SecondInHandler());
                channel.pipeline().addLast(new Integer2StringDecoder());
                channel.pipeline().addLast(new SecondInHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        for (int j=0;j<100;j++){
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(j);
            channel.writeInbound(buf);
        }
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
static String content = "疯狂创客圈：高性能学习社群！";
    @Test
    public void testStringReplayDecoder() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
//                channel.pipeline().addLast(new StringReplayDecoder());
                channel.pipeline().addLast(new StringIntegerHeaderDecoder());
                channel.pipeline().addLast(new SecondInHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        byte[] bytes = content.getBytes(Charset.forName("utf-8"));
        for (int j=0;j<10;j++){
            int random = new Random().nextInt(3);
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(bytes.length*random);
            for (int k=0;k<random;k++){
                buf.writeBytes(bytes);
            }
            channel.writeInbound(buf);
        }
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
static String spliter = "\r\n";
    @Test
    public void testLineBasedFrameDecoder() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
                channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                channel.pipeline().addLast(new StringIntegerHeaderDecoder());
                channel.pipeline().addLast(new SecondInHandler());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
        byte[] bytes = content.getBytes(Charset.forName("utf-8"));
        for (int j=0;j<10;j++){
            int random = new Random().nextInt(3);
            ByteBuf buf = Unpooled.buffer();
            for (int k=0;k<random;k++){
                buf.writeBytes(bytes);
            }
            buf.writeBytes(spliter.getBytes(Charset.forName("utf-8")));
            channel.writeInbound(buf);
        }
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMyEncoder() {
        ChannelInitializer initializer = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel channel) throws Exception {
                channel.pipeline().addLast(new MyEncoder());
            }
        };
        EmbeddedChannel channel = new EmbeddedChannel(initializer);
       for (int j=0;j<100;j++){
           channel.write(j);
       }
       channel.flush();
       ByteBuf buf = channel.readOutbound();
       while (buf!=null){
           System.out.println("o = "+buf.readInt());
           buf = channel.readOutbound();
       }
        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
