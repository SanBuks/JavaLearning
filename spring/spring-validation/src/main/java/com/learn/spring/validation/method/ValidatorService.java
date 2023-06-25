package com.learn.spring.validation.method;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ValidatorService {

    public void test(@NotNull @Valid User user) {
        user.toString();
    }

}
