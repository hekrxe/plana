package com.hekrxe.swagger.controller;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by guoyd on 2019/3/7.
 */
public class BasePageParam implements Serializable {

    @ApiModelProperty(value = "页码")
    private int page = 1;
    @ApiModelProperty(value = "每页数量")
    private int pageSize = 20;


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
