package com.hekrxe.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tanhuayou on 2019/04/13
 */
@Api(value = "用户管理", tags = {"管理"})
public interface Abc {

    @ApiOperation(value = "用户信息", notes = "页面第二行...")
    Person user(@RequestBody User form);
}
