package com.zt.manage.mapper;


import com.zt.manage.domain.pojo.user.User;

/**
 * @author mrzhang
 */
public interface UserMapper {

    /**
     * 根据手机号密码查询账号信息
     *
     * @param phoneNo
     * @param password
     * @return
     */
    User selectByPhoneNoAndPassword(String phoneNo, String password);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    User selectByUserId(String userId);
}
