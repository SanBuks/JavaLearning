package com.learn.java.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.java.mybatisplus.domain.User;
import com.learn.java.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WrapTest {

    @Autowired
    private UserMapper mapper;

    @Test
    void SelectWrapTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                    .between("age", 10, 20)
                    .isNotNull("email");
        List<User> users = mapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void SelectOrderWrapTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = mapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
}
