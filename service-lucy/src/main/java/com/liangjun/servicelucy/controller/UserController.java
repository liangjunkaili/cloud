package com.liangjun.servicelucy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liangjun.servicelucy.DO.User;
import com.liangjun.servicelucy.dao.UserMapper;
import com.liangjun.servicelucy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @RequestMapping("insertUser")
    public int insertUser(){
        return userMapper.insert("liangjun","123456","15600806167");
    }

    @RequestMapping("insertUserByMap")
    public int insertUserByMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","liangjun");
        map.put("password","123456");
        map.put("phone","18550442795");
        return userMapper.insertByMap(map);
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
    public PageInfo<User> findAll(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userService.findAll());
        return pageInfo;
    }
}
