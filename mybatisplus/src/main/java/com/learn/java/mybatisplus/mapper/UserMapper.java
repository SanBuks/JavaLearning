package com.learn.java.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.java.mybatisplus.domain.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {

    @MapKey("id")
    Map<String, Object> selectMapById( Long id);

}
