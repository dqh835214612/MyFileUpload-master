package com.ldf.demo.service.Impl;

import com.ldf.demo.mapper.UserMapper;
import com.ldf.demo.pojo.User;
import com.ldf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author: 清峰
 * @date: 2020/11/2 19:12
 * @code: 愿世间永无Bug!
 * @description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }


}
