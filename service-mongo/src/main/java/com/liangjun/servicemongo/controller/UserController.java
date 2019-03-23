package com.liangjun.servicemongo.controller;

import com.liangjun.servicemongo.DTO.User;
import com.liangjun.servicemongo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author junliang
 * @Date 2019/3/23 3:41 PM
 * @Version 1.0
 **/
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("saveUser")
    public User save(){
        User user = User
                .builder()
                .id(1)
                .age(21)
                .name("jun")
                .build();
        return userDao.save(user);
    }
}
