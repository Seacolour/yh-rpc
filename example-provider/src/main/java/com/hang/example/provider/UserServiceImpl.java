package com.hang.example.provider.service;

import com.hang.example.common.model.User;
import com.hang.example.common.service.UserService;


/**
 * 用户服务实现类
 */
public class UserServiceImpl implements UserService {

    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
