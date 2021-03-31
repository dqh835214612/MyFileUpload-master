package com.ldf.demo.mapper;

import com.ldf.demo.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * @author: 清峰
 * @date: 2020/11/2 19:11
 * @code: 愿世间永无Bug!
 * @description:
 */
@Repository
public interface UserMapper {
    User login(User user);
}
