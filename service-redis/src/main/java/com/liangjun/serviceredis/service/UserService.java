package com.liangjun.serviceredis.service;

import com.liangjun.serviceredis.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public void sayHello(){
        userDao.sayHello();
        System.out.println("hello ...");
    }
}
