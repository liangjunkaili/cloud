package com.liangjun.serviceredis.config.listener;

import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 监听request对象的创建以及销毁
 */
@Component
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        System.out.println("MyServletRequestListener requestDestroyed ..."+request.getRemoteAddr());
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest request = sre.getServletRequest();
        System.out.println("MyServletRequestListener requestInitialized ...");
    }
}
