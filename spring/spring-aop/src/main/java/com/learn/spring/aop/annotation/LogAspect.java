package com.learn.spring.aop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LogAspect {
    // 重用切入点表达式
    @Pointcut(value="execution(public int com.learn.spring.aop.annotation.Calculator.add(..))")
    public void PointCut() { }

//    @Before(value="execution(* com.learn.spring.aop.annotation.*..*(..))") // 通配符格式
    @Before(value="execution(public int com.learn.spring.aop.annotation.Calculator.add(..))")
    @Order(value = 1) // 设定优先级, 越小, 越外层, 越优先
    void beforeBusiness(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object [] args = joinPoint.getArgs();
        System.out.println("前置通知: 方法名 " + methodName + Arrays.toString(args));
    }

    @After(value="PointCut()")
    void afterBusiness(JoinPoint joinPoint) {
        System.out.println(joinPoint.getTarget().getClass());
        String methodName = joinPoint.getSignature().getName();
        System.out.println("后置通知: 方法名 " + methodName );
    }

    @AfterReturning(value="com.learn.spring.aop.annotation.LogAspect.PointCut()", returning = "result")
    void afterReturningBusiness(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("返回通知: 方法名 " + methodName + " 返回对象" + result.toString());
    }

    @AfterThrowing(value="execution(public int com.learn.spring.aop.annotation.Calculator.add(..))", throwing = "ex")
    void afterThrowingBusiness(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("异常通知: 方法名 " + methodName + " 返回对象" + ex.toString());
    }

    @Around(value="execution(public int com.learn.spring.aop.annotation.Calculator.sub(..))")
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