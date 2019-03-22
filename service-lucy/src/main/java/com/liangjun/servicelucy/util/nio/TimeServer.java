package com.liangjun.servicelucy.util.nio;

public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length>0){
            try{
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){
                port = 8080;
            }
        }
//        new Thread(new MultiplexerTimeServer(port),"NIO-TimeServer-001").start();
        new Thread(new AsyncTimeServerHandler(port),"async-timeserver-001").start();
    }
}
