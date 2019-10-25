package com.liangjun.servicelogin.controller;

import com.liangjun.servicelogin.dto.User;
import com.liangjun.servicelogin.service.UserService;
import com.liangjun.servicelogin.util.SignUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 要考虑传输过程中数据的加密、解密
 * 登录态的校验（key是否有效、如何失效）
 */
@RestController("/user")
public class UserController {

    @Autowired
    private UserService userService;
    public void logIn(){

    }

    public void logOut(){

    }

    /**
     * 账号
     * 密码：不小于多少位（8）
     */
    @RequestMapping("/register")
    public void register(String phone,String password,String name){
        String gmt_modified = LocalDateTime.now().toString().replace("T"," ");
        User user = User.builder()
                .name(name)
                .password(SignUtil.encrypt(password,"Md5"))
                .phone(phone)
                .deleted(false)
                .gmtModified(gmt_modified)
                .score(BigDecimal.valueOf(0))
                .build();
        int ret = userService.insertUser(user);
    }

    public void verify(){

    }
}
