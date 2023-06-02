package com.learn.spring.ioc.di.auto.dao;

public class UserDaoImpl implements UserDao {
    @Override
    public void print() {
        System.out.println("Dao!");
    }
}
