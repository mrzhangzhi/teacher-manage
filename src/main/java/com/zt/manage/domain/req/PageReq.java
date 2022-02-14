package com.zt.manage.domain.req;

import lombok.Data;

@Data
public class PageReq {

    private int pageNum = 1;

    private int pageSize = 10;

    private int pageIndex = 0;

    public int getPageNum() {
        if (pageNum < 1) {
            pageNum = 1;
        }
        return pageNum;
    }

    public int getPageSize() {
        if (pageSize < 1) {
            pageSize = 10;
        }
        return pageSize;
    }

    public int getPageIndex() {
        return this.getPageNum() - 1;
    }
}
