package com.liangjun.applicationmonitor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName PeopleController
 * @Description TODO
 * @Author junliang
 * @Date 2019/12/5 2:52 PM
 * @Version 1.0
 **/
@RestController
public class PeopleController {
    @RequestMapping("getPeople")
    public String getPeople(){
        return "people";
    }

}
