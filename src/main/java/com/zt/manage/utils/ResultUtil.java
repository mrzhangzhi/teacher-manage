package com.zt.manage.utils;


import com.zt.manage.domain.resp.ResultResp;
import com.zt.manage.enums.ResultCodeEnum;

/**
 * @author
 */
public class ResultUtil {

    /**
     * 使用默认返回信息
     *
     * @param codeEnum
     * @return
     */
    public static ResultResp error(ResultCodeEnum codeEnum) {
        return new ResultResp(codeEnum.code, codeEnum.msg, null);
    }

    /**
     * 使用默认返回信息
     *
     * @param codeEnum
     * @param obj
     * @return
     */
    public static ResultResp build(ResultCodeEnum codeEnum, Object obj) {
        return new ResultResp(codeEnum.code, codeEnum.msg, obj);
    }

    /**
     * 自定义返回信息
     *
     * @param code
     * @param msg
     * @param obj
     * @return
     */
    public static ResultResp build(Integer code, String msg, Object obj) {
        return new ResultResp(code, msg, obj);
    }
}
