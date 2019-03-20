package com.liangjun.servicelucy.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.liangjun.servicelucy.DO.User;
import com.liangjun.servicelucy.dao.UserMapper;
import com.liangjun.servicelucy.service.UserService;
import com.liangjun.servicelucy.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @RequestMapping("insertUser")
    public int insertUser(@RequestParam(name = "name") String name,
                          @RequestParam(name = "pwd") String pwd,
                          @RequestParam(name = "phone") String phone,
                          @RequestParam(name = "score",required = false) Double score){
        String gmt_modified = LocalDateTime.now().toString().replace("T"," ");
        return userMapper.insert(name, MD5Util.crypt(pwd),phone,false,gmt_modified,BigDecimal.valueOf(score));
    }

    @RequestMapping("check")
    public Boolean check(@RequestParam(name = "pwd") String pwd,
                         @RequestParam(name = "phone") String phone){
        User user = userMapper.findUserByPhone(phone);
        if(user==null){
            return false;
        }
        return user.getPassword().equals(MD5Util.crypt(pwd));
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
    public int insertUserByUser(@Validated User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()){
                System.out.println(fieldError.toString());
            }
            return 0;
        }
        String gmt_modified = LocalDateTime.now().toString().replace("T"," ");
        User user1 = User.builder()
                .name(user.getName())
                .password(MD5Util.crypt(user.getPassword()))
                .phone(user.getPhone())
                .deleted(false)
                .gmtModified(gmt_modified)
                .score(user.getScore())
                .build();
        return userMapper.insertByUser(user1);
    }

    @RequestMapping("findUserByPhone")
    public User findUserByPhone(){
        return userMapper.findUserByPhone("15600806167");
    }
    @RequestMapping("deleteUserByPhone")
    public int deleteUserByPhone(@RequestParam(name = "phone") String phone){
        return userService.deleteUserByPhone(phone);
    }

    @RequestMapping("findAll")
    public PageInfo<User> findAll(@RequestParam(defaultValue = "1") int pageNo,@RequestParam(defaultValue = "10") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<User> pageInfo = new PageInfo<>(userService.findAll());
        return pageInfo;
    }
}
