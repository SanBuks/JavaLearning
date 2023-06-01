package com.learn.spring.ioc.di.container;

import java.util.List;

public class Department {
    private String name;
    private List<User> emp;

    public Department() { }

    public Department(String name) {
        this.name = name;
    }

    public List<User> getEmp() {
        return emp;
    }

    public void setEmp(List<User> emp) {
        this.emp = emp;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", emp=" + emp +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
