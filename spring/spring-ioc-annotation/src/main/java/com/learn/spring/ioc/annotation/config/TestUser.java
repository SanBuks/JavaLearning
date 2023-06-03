package com.learn.spring.ioc.annotation.config;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestUser {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        User user = (User) context.getBean("user");
        user.print();
    }
}
