package com.liangjun.servicehi.event;

import org.springframework.context.ApplicationEvent;

/**
 * 编写事件源
 */
public class CustomEvent extends ApplicationEvent {
    private MessageEntity messageEntity;
    public CustomEvent(Object source,MessageEntity messageEntity) {
        super(source);
        this.messageEntity = messageEntity;
    }
    public MessageEntity getMessageEntity(){
        return this.messageEntity;
    }
}
