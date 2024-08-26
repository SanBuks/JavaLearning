package org.learn.java.springmvc.controller;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String email;
}
