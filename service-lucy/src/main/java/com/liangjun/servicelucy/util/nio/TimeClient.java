package com.liangjun.servicelucy.util.nio;

public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length>0){
            try{
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e){
                port = 8080;
            }
        }
//        new Thread(new TimeClientHandle("127.0.0.1",port),"timeclient-001").start();
        new Thread(new AsyncTimeClientHandler("127.0.0.1",port),"async-timeclient-001").start();
    }
}
