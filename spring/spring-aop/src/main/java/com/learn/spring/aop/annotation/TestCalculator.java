package com.learn.spring.aop.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestCalculator {

    @Test
    void testAspect(){
        ApplicationContext context = new AnnotationConfigApplicationContext("com.learn.spring.aop.annotation");
        ICalculator calculator = (ICalculator) context.getBean("calculator");
        calculator.add(1, 2);
    }
    @Test
    void testAroundAspect(){
        ApplicationContext context = new AnnotationConfigApplicationContext("com.learn.spring.aop.annotation");
        ICalculator calculator = (ICalculator) context.getBean("calculator");
        calculator.sub(2, 1);
    }
}
