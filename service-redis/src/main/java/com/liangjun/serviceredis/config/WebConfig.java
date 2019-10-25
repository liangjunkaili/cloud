package com.liangjun.serviceredis.config;

import com.liangjun.serviceredis.service.RedisInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RedisInterceptor redisInterceptor;
    @Autowired
    private MyCallableProcessingInterceptor callableProcessingInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors ...");
        registry.addInterceptor(redisInterceptor).addPathPatterns("/**");
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        System.out.println("configurePathMatch ...");
        PathMatcher pathMatcher = configurer.getPathMatcher();
        UrlPathHelper urlPathHelper = configurer.getUrlPathHelper();
        configurer.setUseSuffixPatternMatch(true);
        System.out.println(configurer.isUseRegisteredSuffixPatternMatch()+"--"+configurer.isUseSuffixPatternMatch()+"--"+configurer.isUseTrailingSlashMatch());
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.registerCallableInterceptors(callableProcessingInterceptor);
    }
}
