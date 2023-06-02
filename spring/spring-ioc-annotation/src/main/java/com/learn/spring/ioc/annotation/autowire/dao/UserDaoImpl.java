package com.learn.spring.ioc.annotation.autowire.dao;

public class UserDaoImpl implements UserDao {
    @Override
    public void print() {
        System.out.println("Dao!");
    }
}
