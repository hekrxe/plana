package com.in.action.server.handler;

import com.in.action.codec.Pack;

/**
 * User: tanhuayou
 * Date: 2018/7/4
 */
public class Dispatcher {

    public void dispatch(Pack pack) {
        System.out.println("Dispatcher cmd=[" + pack.getCmd() + "]");
        System.out.println("Dispatcher body=[" + new String(pack.getBody()) + "]");
    }
}
