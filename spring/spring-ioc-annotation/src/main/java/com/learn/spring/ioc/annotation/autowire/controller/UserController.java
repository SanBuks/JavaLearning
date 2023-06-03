package com.learn.spring.ioc.annotation.autowire.controller;

import com.learn.spring.ioc.annotation.autowire.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    // 构造方法注入
    //  @Autowired
    //  public UserController(UserService userService) {
    //      this.userService = userService;
    //  }

    // 形参注入/单参构造可无注解
    public UserController(/*@Autowired*/ UserService userService) {
        this.userService = userService;
    }

    // 字段注入
    // @Autowired
    private UserService userService;

    public void print() {
        System.out.println("Controller!");
        userService.print();
    }
}
