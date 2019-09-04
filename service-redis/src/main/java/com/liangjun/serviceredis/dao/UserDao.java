package com.liangjun.serviceredis.dao;

import org.springframework.stereotype.Component;

@Component
public class UserDao {
    public void sayHello(){
        System.out.println("dao hello ...");
    }
}
