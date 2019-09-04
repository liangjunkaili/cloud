package com.liangjun.serviceredis.config;

import org.springframework.stereotype.Service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Service
public class RedisListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("RedisListener contextInitialized ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("RedisListener contextDestroyed ...");
    }
}
