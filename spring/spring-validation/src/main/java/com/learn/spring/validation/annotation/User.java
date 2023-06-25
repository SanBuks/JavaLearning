package com.learn.spring.validation.annotation;

import jakarta.validation.constraints.*;

public class User {

    @NotBlank
    @NotNull
    private String name;

    @Max(150)
    @Min(0)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
