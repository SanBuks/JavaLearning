package com.learn.spring.ioc.di.auto;

import com.learn.spring.ioc.di.auto.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMVC {
    @Test
    void testMvc() {
        ApplicationContext app = new ClassPathXmlApplicationContext("bean-auto.xml");
        UserController userController = (UserController) app.getBean("user_controller");
        userController.print();
    }
}
