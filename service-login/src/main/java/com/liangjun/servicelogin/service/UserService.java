package com.liangjun.servicelogin.service;

import com.github.pagehelper.Page;
import com.liangjun.servicelogin.dto.User;

public interface UserService {
    Page<User> findAll();
    int deleteUserByPhone(String phone);
    int insertUser(User user);
}
