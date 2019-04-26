package com.hekrxe.swagger.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "客服工作报告")
public class TenantStatisticPageForm extends BasePageParam {

    @ApiModelProperty("组合")
    private BasePageParam basePageParam;

    /**
     * 起始时间，毫秒单位
     */
    @ApiModelProperty("起始时间，毫秒单位")
    private Long startTime;
    /**
     * 截止时间，毫秒单位
     */
    @ApiModelProperty("起始时间，毫秒单位")
    private Long endTime;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
