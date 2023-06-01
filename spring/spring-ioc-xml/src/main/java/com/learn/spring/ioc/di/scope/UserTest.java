package com.learn.spring.ioc.di.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    public void testScope() {
        ApplicationContext context = new ClassPathXmlApplicationContext("scope.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }
}
