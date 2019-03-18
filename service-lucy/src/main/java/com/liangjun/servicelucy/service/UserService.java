package com.liangjun.servicelucy.service;

import com.github.pagehelper.Page;
import com.liangjun.servicelucy.DO.User;

public interface UserService {
    Page<User> findAll();
}
