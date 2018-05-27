package com.hekrxe.plana.minor.spi;

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
