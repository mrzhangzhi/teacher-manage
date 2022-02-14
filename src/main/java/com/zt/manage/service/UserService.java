package com.zt.manage.service;

import com.zt.manage.domain.pojo.user.User;
import com.zt.manage.domain.req.user.LoginReq;
import com.zt.manage.mapper.UserMapper;
import com.zt.manage.utils.Md5Util;
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
        return userMapper.selectByPhoneNoAndPassword(req.getUsername(), Md5Util.getMd5(req.getPassword()));
    }

    public User selectByUserId(String userId) {
        return userMapper.selectByUserId(userId);
    }
}
