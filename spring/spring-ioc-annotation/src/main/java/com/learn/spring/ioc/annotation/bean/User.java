package com.learn.spring.ioc.annotation.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component(value="user") // 默认 为 类名小写
//@Controller
//@Service
//@Repository
public class User {
    public void print() {
        System.out.println("user");
    }
}
