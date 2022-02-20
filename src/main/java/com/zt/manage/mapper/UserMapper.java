package com.zt.manage.mapper;


import com.zt.manage.domain.dto.user.UserListDTO;
import com.zt.manage.domain.pojo.user.SysUser;
import com.zt.manage.domain.req.user.UserListQueryReq;
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
    SysUser selectByPhoneNoAndPassword(@Param("phoneNo") String phoneNo, @Param("password") String password);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    SysUser selectByUserId(@Param("userId") String userId);

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
    Integer insert(SysUser req);

    /**
     * 修改用户信息
     *
     * @param req
     * @return
     */
    Integer update(SysUser req);

    /**
     * 根据用户id删除用户
     *
     * @param userId
     * @return
     */
    int deleteUser(@Param("userId") String userId);

    /**
     * 删除用户所有权限
     *
     * @param userId
     */
    void deleteUserRole(@Param("userId") String userId);

    /**
     * 批量新增用户权限
     *
     * @param userId
     * @param roleList
     */
    void insertUserRole(@Param("userId") String userId, @Param("roleList") List<Integer> roleList);
}
