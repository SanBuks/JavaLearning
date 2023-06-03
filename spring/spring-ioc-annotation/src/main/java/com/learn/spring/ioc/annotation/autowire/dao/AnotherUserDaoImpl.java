package com.learn.spring.ioc.annotation.autowire.dao;

import org.springframework.stereotype.Repository;

@Repository
public class AnotherUserDaoImpl implements UserDao {

    @Override
    public void print() {
        System.out.println("Another Dao");
    }
}
