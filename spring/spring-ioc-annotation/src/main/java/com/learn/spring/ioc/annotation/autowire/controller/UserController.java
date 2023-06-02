package com.learn.spring.ioc.annotation.autowire.controller;

import com.learn.spring.ioc.di.auto.service.UserService;

public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void print() {
        System.out.println("Controller!");
        userService.print();
    }
}
