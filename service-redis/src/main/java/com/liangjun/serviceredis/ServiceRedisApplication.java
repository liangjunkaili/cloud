package com.liangjun.serviceredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 默认扫描所有的类，为了减少加载时间，建议指定扫描的路径
 */
@SpringBootApplication(scanBasePackages = {"com.liangjun.serviceredis.controller","com.liangjun.serviceredis.service",
        "com.liangjun.serviceredis.dao","com.liangjun.serviceredis.config"})
public class ServiceRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRedisApplication.class, args);
    }

}
