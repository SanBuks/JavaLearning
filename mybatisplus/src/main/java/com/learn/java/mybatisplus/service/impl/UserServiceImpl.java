package com.learn.java.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.java.mybatisplus.domain.User;
import com.learn.java.mybatisplus.mapper.UserMapper;
import com.learn.java.mybatisplus.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
