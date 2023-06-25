package com.learn.spring.validation.method;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ValidatorMethodTest {

    @Test
    public void test() {
        ApplicationContext app = new AnnotationConfigApplicationContext(Config.class);
        ValidatorService service = app.getBean(ValidatorService.class);
        User user = new User();
        user.setAge(-1);
        service.test(user);
    }

}
