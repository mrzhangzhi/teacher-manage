package com.zt.manage.service;

import com.zt.manage.domain.pojo.user.User;
import com.zt.manage.domain.req.user.LoginReq;
import com.zt.manage.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author mrzhang
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User login(LoginReq req) {
        return userMapper.selectByPhoneNoAndPassword(req.getUsername(), req.getPassword());
    }
}
