package com.learn.spring.ioc.di.clazz;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    @Test
    public void testOne2OneOutBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-clazz.xml");
        User user = context.getBean("user_out", User.class);
        System.out.println(user);
    }

    @Test
    public void testOne2OneInnerBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-clazz.xml");
        User user = context.getBean("user_in", User.class);
        System.out.println(user);
    }

    @Test
    public void testOne2OneCascadeBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-clazz.xml");
        User user = context.getBean("user_cascade", User.class);
        System.out.println(user);
    }
}

