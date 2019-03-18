package com.liangjun.servicelucy.service.impl;

import com.github.pagehelper.Page;
import com.liangjun.servicelucy.DO.User;
import com.liangjun.servicelucy.dao.UserMapper;
import com.liangjun.servicelucy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Page<User> findAll() {
        return userMapper.findAll();
    }
}
