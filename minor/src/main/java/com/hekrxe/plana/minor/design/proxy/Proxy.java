package com.hekrxe.plana.minor.design.proxy;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Proxy implements Action {
    private Action action;

    public Proxy(Action action) {
        this.action = action;
    }

    @Override
    public void action() {
        System.out.println("before");
        action.action();
        System.out.println("after");
    }
}
