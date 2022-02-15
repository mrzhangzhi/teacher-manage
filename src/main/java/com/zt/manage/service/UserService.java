package com.zt.manage.service;

import com.zt.manage.constants.CommonConstant;
import com.zt.manage.domain.dto.user.UserListDTO;
import com.zt.manage.domain.dto.user.UserUpdateDTO;
import com.zt.manage.domain.pojo.user.User;
import com.zt.manage.domain.req.user.*;
import com.zt.manage.domain.resp.PageResp;
import com.zt.manage.mapper.UserMapper;
import com.zt.manage.utils.Md5Util;
import com.zt.manage.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author mrzhang
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User selectByLoginInfo(LoginReq req) {
        return userMapper.selectByPhoneNoAndPassword(req.getUsername(), Md5Util.getMd5(req.getPassword()));
    }

    public User selectByUserId(String userId) {
        return userMapper.selectByUserId(userId);
    }

    public PageResp<UserListDTO> selectListByQuery(UserListQueryReq req) {
        PageResp<UserListDTO> resp = new PageResp<>();
        resp.setPageNum(req.getPageNum());
        int total = userMapper.selectCountByQuery(req);
        resp.setTotal(total);
        if (total == 0) {
            return resp;
        }
        List<UserListDTO> list = userMapper.selectListByQuery(req);
        resp.setData(list);
        return resp;
    }

    public Integer updateLockStatus(UserStatusUpdateReq req) {
        UserUpdateDTO update = new UserUpdateDTO();
        update.setUserId(req.getUserId());
        update.setLockStatus(req.getLockStatus() == CommonConstant.ZERO ? CommonConstant.ZERO : CommonConstant.ONE);
        return userMapper.update(update);
    }

    public Integer insert(UserInsertReq req) {
        req.setUserId(UUIDUtil.getUuid());
        req.setPassword(CommonConstant.DEFAULT_PASSWORD);
        return userMapper.insert(req);
    }

    public Integer update(UserUpdateReq req) {
        UserUpdateDTO update = new UserUpdateDTO();
        update.setUserId(req.getUserId());
        update.setUserName(req.getUserName());
        return userMapper.update(update);
    }

    public Integer updatePassword(UserPasswordUpdateReq req) {
        User user = userMapper.selectByUserId(req.getUserId());
        if (!StringUtils.equals(user.getPassword(), Md5Util.getMd5(req.getOldPassword()))) {
            return null;
        }

        UserUpdateDTO update = new UserUpdateDTO();
        update.setUserId(req.getUserId());
        update.setPassword(Md5Util.getMd5(req.getPassword()));
        return userMapper.update(update);
    }
}
