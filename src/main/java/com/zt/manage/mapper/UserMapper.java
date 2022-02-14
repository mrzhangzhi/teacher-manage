package com.zt.manage.mapper;


import com.zt.manage.domain.pojo.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    User selectByPhoneNoAndPassword(@Param("phoneNo") String phoneNo, @Param("password") String password);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    User selectByUserId(@Param("userId") String userId);

    /**
     * 根据用户id查询权限id列表
     *
     * @param userId
     * @return
     */
    List<Integer> selectRoleByUserId(@Param("userId") String userId);
}
