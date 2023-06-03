package com.learn.spring.ioc.annotation.config;

import org.springframework.stereotype.Component;

@Component
public class User {
    public void print() {
        System.out.println("user");
    }
}
