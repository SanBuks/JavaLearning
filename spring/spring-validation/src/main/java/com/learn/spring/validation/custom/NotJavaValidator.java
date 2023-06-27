package com.learn.spring.validation.custom;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotJavaValidator implements ConstraintValidator<NotJava, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if (value == null) return false;
        if (value.contains("java")) {
            String str = ctx.getDefaultConstraintMessageTemplate();
            System.out.println(str);
            ctx.disableDefaultConstraintViolation();
            ctx.buildConstraintViolationWithTemplate("xxx").addConstraintViolation();
            return false;
        }
        return true;
    }

}
