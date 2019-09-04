package com.liangjun.serviceredis.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RedisAspect {

    //如何编写切点表达式
    @Pointcut("execution(* com.liangjun.serviceredis.controller.UserController.*(..))")
    public void pointCut(){}

    @Around("pointCut()")
    public void around(ProceedingJoinPoint pjp){
        System.out.println("Around 1 ...");
        try {
            pjp.proceed();
        } catch (Throwable throwable) {
            System.out.println("Around 3 ...");
            throwable.printStackTrace();
        }
        System.out.println("Around 2 ...");
    }

    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void doAfter(Object ret) {
        System.out.println("doAfter"+ret);
    }
}
