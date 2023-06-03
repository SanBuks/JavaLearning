package com.learn.spring.ioc.annotation.autowire.service;

import com.learn.spring.ioc.annotation.autowire.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    // set 方法注入
    @Autowired
    @Qualifier(value="anotherUserDaoImpl")
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void print() {
        System.out.println("Service!");
        userDao.print();
    }
}
