package com.itheima.service;

import com.itheima.domain.User;

/**
 * @author zhangjian
 * @version V1.0.0
 * @Date Created in 2018/8/27
 * @description:
 */
public interface UserService {
    User checkCode(String user_code);

    void save(User user);

    User login(User user);
}
