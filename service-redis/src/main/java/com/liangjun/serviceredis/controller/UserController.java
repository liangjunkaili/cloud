package com.liangjun.serviceredis.controller;

import com.liangjun.serviceredis.config.listener.MyEvent;
import com.liangjun.serviceredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ApplicationContext applicationContext;
    @GetMapping("sayHello")
    public void sayHello(){
        applicationContext.publishEvent(new MyEvent("sayHello event ..."));
        userService.sayHello();
    }
}
