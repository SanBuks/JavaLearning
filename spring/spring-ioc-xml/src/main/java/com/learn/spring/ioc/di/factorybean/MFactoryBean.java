package com.learn.spring.ioc.di.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class MFactoryBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
