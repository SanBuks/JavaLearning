package com.learn.spring.validation.custom;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ValidatorCustomService {
    public void test(@Valid User user) {
        user.toString();
    }
}
