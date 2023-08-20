package com.learn.java.mybatisplus;

import com.learn.java.mybatisplus.domain.User;
import com.learn.java.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisplusApplicationTests {

    @Autowired
    private UserMapper mapper;

    @Test
    void SimpleCRUDTest() {
        List<User> users = mapper.selectList(null);
        users.forEach(System.out::println);
    }
}
