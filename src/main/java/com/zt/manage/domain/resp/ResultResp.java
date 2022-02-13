package com.zt.manage.domain.resp;

import lombok.Data;

/**
 * @author mrzhang
 */
@Data
public class ResultResp<T> {
    private Integer code;
    private String msg;
    private T data;

    public ResultResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultResp(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
