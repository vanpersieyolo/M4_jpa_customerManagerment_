package com.codegym.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyLogger {
    @AfterReturning(pointcut = "within(com.codegym.controller.*)",returning = "abc")
    public void logger(JoinPoint joinPoint, Object abc){
        System.out.println("bắt đầu");
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String method = joinPoint.getSignature().getName();
        System.out.println(className + " " +method );
        if (abc == null){
            System.out.println("null rồi");
        }else
            System.out.println(abc.hashCode());
    }
}
