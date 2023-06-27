package com.learn.spring.validation.custom;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValidatorMethodTest {

    @Test
    public void test() {
        ApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        ValidatorCustomService service = app.getBean(ValidatorCustomService.class);
        User user = new User();
        user.setAge(3);
        user.setPhone("13333333333");
        service.test(user);
    }

}
