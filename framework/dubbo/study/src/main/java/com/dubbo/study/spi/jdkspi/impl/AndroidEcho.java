package com.dubbo.study.spi.jdkspi.impl;

import com.dubbo.study.spi.jdkspi.Echo;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public class AndroidEcho implements Echo {
    /**
     * 回声
     *
     * @param msg msg
     * @return ${who} msg
     */
    @Override
    public String echo(String msg) {
        return "Android " + msg;
    }
}
