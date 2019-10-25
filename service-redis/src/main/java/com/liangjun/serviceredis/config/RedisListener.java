package com.liangjun.serviceredis.config;

import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Service
public class RedisListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        System.out.println("RedisListener contextInitialized ..."+sce.getSource());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("RedisListener contextDestroyed ...");
    }
}
