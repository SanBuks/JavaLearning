package com.learn.spring.ioc.di.container;

import com.learn.spring.ioc.di.clazz.Department;

public class User {
    private Integer id;
    private String name;
    private Department department;

    public User() { }

    public User(Integer id, String name, Department department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
