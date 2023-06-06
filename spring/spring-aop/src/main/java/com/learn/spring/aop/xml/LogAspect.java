package com.learn.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LogAspect {

    void beforeBusiness(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();
        System.out.println("前置通知: 方法名 " + methodName + Arrays.toString(args));
    }

    void afterBusiness(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知: 方法名 " + methodName );
    }

    void afterReturningBusiness(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知: 方法名 " + methodName + " 返回对象" + result.toString());
    }

    void afterThrowingBusiness(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常通知: 方法名 " + methodName + " 返回对象" + ex.toString());
    }

    Object aroundBusiness(ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();
        String argsString = Arrays.toString(args);
        Object result = null;
        try {
            System.out.println("前置通知: 方法名 " + methodName + argsString);
            result = joinPoint.proceed(args);
            System.out.println("返回通知: 方法名 " + methodName + " 返回对象 " + result.toString());
        } catch (Throwable throwable) {
            System.out.println("异常通知: 方法名 " + methodName + " 异常对象 " + throwable);
        } finally {
            System.out.println("后置通知: 方法名 " + methodName );
        }
        return result;
    }
}