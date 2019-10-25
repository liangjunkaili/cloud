package com.liangjun.servicelogin.service.impl;

import com.github.pagehelper.Page;
import com.liangjun.servicelogin.dao.UserMapper;
import com.liangjun.servicelogin.dto.User;
import com.liangjun.servicelogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Page<User> findAll() {
        return userMapper.findAll();
    }

    @Transactional
    @Override
    public int deleteUserByPhone(String phone) {
        User user = userMapper.findUserByPhone(phone);
        if(user!=null){
            int code = userMapper.deleteUserByPhone(phone);
            return code;
        }
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertByUser(user);
    }
}
