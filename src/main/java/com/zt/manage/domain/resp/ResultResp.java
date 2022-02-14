package com.zt.manage.domain.resp;

import lombok.Data;

/**
 * @author mrzhang
 */
@Data
public class ResultResp {
    private Integer code;
    private String msg;
    private Object data;

    public ResultResp(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultResp(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
