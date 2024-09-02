package org.learn.java.springmvc.domain;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private String password;
    private String sex;
    private Integer age;
    private String email;
    private List<String> hobby;
}
