package com.learn.spring.ioc.annotation.autowire;

import com.learn.spring.ioc.annotation.autowire.controller.UserController;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {
    @Test
    void testMvc() {
        ApplicationContext app = new ClassPathXmlApplicationContext("autowire.xml");
        UserController userController = (UserController) app.getBean("userController");
        userController.print();
    }
}
