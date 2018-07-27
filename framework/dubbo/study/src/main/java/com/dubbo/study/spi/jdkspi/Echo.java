package com.dubbo.study.spi.jdkspi;

/**
 * User: tanhuayou
 * Date: 2018/5/25
 */
public interface Echo {
    /**
     * 回声
     *
     * @param msg msg
     * @return ${who} msg
     */
    String echo(String msg);
}
