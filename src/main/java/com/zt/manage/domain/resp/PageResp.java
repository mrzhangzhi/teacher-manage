package com.zt.manage.domain.resp;

import lombok.Data;

import java.util.List;

@Data
public class PageResp<T> {

    private Integer total;

    private Integer pageNum;

    private List<T> data;
}
