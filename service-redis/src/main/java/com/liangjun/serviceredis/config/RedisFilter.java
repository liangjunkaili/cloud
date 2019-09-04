package com.liangjun.serviceredis.config;

import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

@Service
public class RedisFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("RedisFilter init ..."+filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("RedisFilter doFilter ...");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("RedisFilter destroy ...");
    }
}
