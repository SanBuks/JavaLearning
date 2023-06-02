package com.learn.spring.ioc.annotation.autowire.service;

import com.learn.spring.ioc.di.auto.dao.UserDao;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void print() {
        System.out.println("Service!");
        userDao.print();
    }
}
