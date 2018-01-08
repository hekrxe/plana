package com.hekrxe.plana.minor.design.proxy;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Target implements Action {
    @Override
    public void action() {
        System.out.println(getClass().getSimpleName());
    }
}
