package com.learn.spring.validation.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValidatorTest {
    @Test
    public void testOrigin() {
        ApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        ValidatorOrigin va = app.getBean(ValidatorOrigin.class);

        User user = new User();
//        user.setAge(10);
//        user.setName("xxx");
        boolean result = va.validatorByUserOrigin(user);

        System.out.println(result);
    }

    @Test
    public void testSpring() {
        ApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        ValidatorSpring va = app.getBean(ValidatorSpring.class);

        User user = new User();
        user.setAge(10);
        user.setName("xx");
        boolean result = va.validatorByUserSpring(user);

        System.out.println(result);
    }
}
