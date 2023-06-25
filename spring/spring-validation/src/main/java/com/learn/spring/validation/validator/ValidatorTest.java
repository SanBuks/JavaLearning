package com.learn.spring.validation.validator;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class ValidatorTest {

    @Test
    public void test() {
        User user = new User();

        user.setName("lucky");
        user.setAge(1);

        DataBinder dataBinder = new DataBinder(user);
        dataBinder.setValidator(new PersonValidator());
        dataBinder.validate();
        BindingResult result = dataBinder.getBindingResult();

        System.out.println(result.getAllErrors());
    }
}
