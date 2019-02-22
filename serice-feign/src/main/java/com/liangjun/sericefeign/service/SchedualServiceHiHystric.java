package com.liangjun.sericefeign.service;

import org.springframework.stereotype.Component;

/**
 * Feign中使用断路器
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry"+name;
    }
}
