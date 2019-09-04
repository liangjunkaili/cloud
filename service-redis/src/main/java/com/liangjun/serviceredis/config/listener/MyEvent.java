package com.liangjun.serviceredis.config.listener;

import org.springframework.context.ApplicationEvent;


public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
    public void event(){
        System.out.println("MyEvent ....");
    }
}
