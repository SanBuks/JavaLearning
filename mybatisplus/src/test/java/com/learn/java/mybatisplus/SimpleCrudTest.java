package com.learn.java.mybatisplus;

import com.learn.java.mybatisplus.domain.User;
import com.learn.java.mybatisplus.mapper.UserMapper;
import com.learn.java.mybatisplus.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SimpleCrudTest {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private IUserService service;

    @Test
    void SimpleSelectTest() {
        List<User> users = mapper.selectList(null);
        users.forEach(System.out::println);

        Map<String, Object> map = new HashMap<>();
        map.put("age", 20);
        users = mapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    @Test
    void SimpleInsertTest() {
        User user = new User();
        user.setAge(32);
        user.setName("大强");
        mapper.insert(user);
        mapper.deleteById(user);
    }

    @Test
    void SimpleUpdateTest() {
        User user = new User();
        user.setId(1L);
        user.setAge(32);
        user.setName("jordan");
        mapper.updateById(user);
    }

    @Test
    void CustomTest() {
        Map<String, Object> map = mapper.selectMapById(1L);
        System.out.println(map);
    }

    @Test
    void ServiceTest() {
        User user = new User();
        user.setAge(30);
        user.setName("大兵");
        service.saveOrUpdate(user);
    }

    @Test
    void LogicDelete() {
        mapper.deleteById(1693372796688965633L);
        List<User> users = mapper.selectList(null);
        users.forEach(System.out::println);
    }
}
