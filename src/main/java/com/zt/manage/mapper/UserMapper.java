package com.zt.manage.mapper;


import com.zt.manage.domain.dto.user.UserListDTO;
import com.zt.manage.domain.dto.user.UserUpdateDTO;
import com.zt.manage.domain.pojo.user.User;
import com.zt.manage.domain.req.user.UserInsertReq;
import com.zt.manage.domain.req.user.UserListQueryReq;
import com.zt.manage.domain.req.user.UserUpdateReq;
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

    /**
     * 根据条件查询用户总数
     *
     * @param req
     * @return
     */
    int selectCountByQuery(UserListQueryReq req);

    /**
     * 根据条件查询用户列表
     *
     * @param req
     * @return
     */
    List<UserListDTO> selectListByQuery(UserListQueryReq req);

    /**
     * 新增用户
     *
     * @param req
     * @return
     */
    Integer insert(UserInsertReq req);

    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    Integer update(UserUpdateDTO req);
}
