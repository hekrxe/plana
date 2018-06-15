package com.service;

import com.api.HelloService;

/**
 * User: tanhuayou
 * Date: 2018/6/15
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String content) {
        System.out.println("hello " + content);
        return "hi " + content;
    }
}
