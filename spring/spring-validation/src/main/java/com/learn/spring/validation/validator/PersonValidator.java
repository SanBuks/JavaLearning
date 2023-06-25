package com.learn.spring.validation.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", "user.name.null", "name is null!");
        User user = (User) target;
        if (user.getAge() < 0) {
            errors.rejectValue("age", "user.age.lt.zero", "age is negative!");
        } else if (user.getAge() > 150) {
            errors.rejectValue("age", "user.age.gt.150", "age is too big!");
        }
    }
}
