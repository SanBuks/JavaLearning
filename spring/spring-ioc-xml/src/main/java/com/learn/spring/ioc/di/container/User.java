package com.learn.spring.ioc.di.container;

import com.learn.spring.ioc.di.clazz.Department;

import java.util.Arrays;
import java.util.List;

public class User {
    private String[] hobbies;

    public User() { }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "User{" +
                "hobbies=" + Arrays.toString(hobbies) +
                '}';
    }
}
