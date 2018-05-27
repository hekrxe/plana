package com.hekrxe.plana.minor.spi.impl;


import com.hekrxe.plana.minor.spi.Echo;

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
