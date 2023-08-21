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
    void SelectQueryWrapTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                    .between("age", 10, 20)
                    .isNotNull("email");
        List<User> users = mapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void SelectOrderQueryWrapTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = mapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    @Test
    void DeleteQueryWrapTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int n = mapper.delete(queryWrapper);
        System.out.println(n);
    }

    @Test
    void UpdateQueryWrapTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", "test1@baomidou.com");
        User user = new User();
        user.setEmail("test@test.com");
        int n = mapper.update(user, queryWrapper);
        System.out.println(n);
    }

    @Test
    void SelectQueryWrapCombineTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                    .or(i -> i.ge("age", 20)
                              .isNotNull("email"));
        mapper.selectList(queryWrapper).forEach(System.out::println);
    }

    @Test
    void SelectMapsTest() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "a")
                    .or(i -> i.ge("age", 20)
                              .isNotNull("email"))
                    .select("name");
        System.out.println(mapper.selectMaps(queryWrapper));
    }
}
