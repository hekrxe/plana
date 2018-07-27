package com.provider;


import com.alibaba.dubbo.config.annotation.Service;
import com.services.HelloService;

/**
 * User: tanhuayou
 * Date: 2018/5/24
 */

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String hi) {
        return "aaa:" + hi;
    }
}
