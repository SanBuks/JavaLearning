package com.learn.spring.ioc.di.life;

public class User {
    private String name;

    public User() {
        System.out.println("1. 调用无参构造函数");
    }


    public void setName(String name) {
        System.out.println("2. set 属性");
        this.name = name;
    }

    public void init() {
        System.out.println("4. 初始化方法");
    }

    public void destroy() {
        System.out.println("7. 销毁方法");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
