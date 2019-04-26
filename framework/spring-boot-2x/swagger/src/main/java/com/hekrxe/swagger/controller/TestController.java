package com.hekrxe.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tanhuayou on 2019/04/13
 */
@RestController
@Api(value = "用户管理", tags = {"管理"}, produces = "")
public class TestController {


//    @GetMapping("/user")
//    @ApiOperation(value = "用户信息", notes = "页面第二行...")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "起始时间，毫秒单位", required = true, dataType = "long"),
//    })
//    public User user(@RequestBody User form) {
//        return new User();
//    }

    @GetMapping("/abc")
    @ApiOperation(value = "abc用户信息", notes = "页面第二行...")
    public GeneObj<Gender> abc(@ApiParam(name = "f1", value = "desc") String f13) {
        return null;
    }

//    @GetMapping("/sdfs")
//    @ApiOperation(value = "sds用户信息", notes = "页面第二行...")
//    @ApiImplicitParam()
//    public Map<String, Object> sds(String f13) {
//        return null;
//    }


}
