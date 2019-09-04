package com.liangjun.serviceredis.config;

import com.liangjun.serviceredis.service.RedisInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RedisInterceptor redisInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors ...");
        registry.addInterceptor(redisInterceptor).addPathPatterns("/**");
    }
}
