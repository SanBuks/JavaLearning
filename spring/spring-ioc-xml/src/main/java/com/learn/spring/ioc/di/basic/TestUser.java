package com.learn.spring.ioc.di.basic;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // id 获取 bean
        User user = (User) context.getBean("user");
        user.print();
        // class 获取 bean (只能有一个该类型 bean)
        User user2 = context.getBean(User.class);
        user2.print();
        // 两种结合获取 bean
        User user3 = context.getBean("user", User.class);
        user3.print();
    }

    @Test
    public void testSetDI() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_di.xml");
        User user = (User) context.getBean("user_set", User.class);
        System.out.println(user);
    }

    @Test
    public void testCtorDI() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean_ctor.xml");
        User user = (User) context.getBean("user_ctor", User.class);
        System.out.println(user);
    }
}

