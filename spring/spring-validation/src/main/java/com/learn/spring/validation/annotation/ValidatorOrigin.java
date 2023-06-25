package com.learn.spring.validation.annotation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidatorOrigin {

    @Autowired
    private Validator validator;

    public boolean validatorByUserOrigin(User user) {
        Set<ConstraintViolation<User>> result = validator.validate(user);
        return result.isEmpty();
    }

}
