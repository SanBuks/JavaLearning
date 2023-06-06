package com.learn.spring.aop.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCalculator {

    @Test
    void testAspect(){
        ApplicationContext context = new ClassPathXmlApplicationContext("xml-aop.xml");
        ICalculator calculator = (ICalculator) context.getBean("calculator");
        calculator.add(1, 2);
    }
    @Test
    void testAroundAspect(){
        ApplicationContext context = new ClassPathXmlApplicationContext("xml-aop.xml");
        ICalculator calculator = (ICalculator) context.getBean("calculator");
        calculator.sub(2, 1);
    }
}
