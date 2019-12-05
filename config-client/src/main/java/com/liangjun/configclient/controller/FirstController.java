package com.liangjun.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FirstController
 * @Description TODO
 * @Author junliang
 * @Date 2019/12/5 2:59 PM
 * @Version 1.0
 **/
@RestController
public class FirstController {
    @Value("${from}")
    String from;
    @RequestMapping("hi")
    public String hi(){
        return from;
    }

}
