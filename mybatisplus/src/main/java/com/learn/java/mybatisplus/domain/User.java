package com.learn.java.mybatisplus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
