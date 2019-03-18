package com.liangjun.servicemiya.controller;

import com.liangjun.servicemiya.DO.User;
import com.liangjun.servicemiya.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("insertUser")
    public int insertUser(){
        return userMapper.insert("liangjun","123456","15600806167");
    }


    @RequestMapping("insertUserByUser")
    public int insertUserByUser(){
        User user = User.builder()
                .name("liangwei")
                .password("123456")
                .phone("17710670214")
                .build();
        return userMapper.insertByUser(user);
    }

    @RequestMapping("findUserByPhone")
    public User findUserByPhone(){
        return userMapper.findUserByPhone("15600806167");
    }

    @RequestMapping("findAll")
    public List<User> findAll(){
        return userMapper.findAll();
    }
}
