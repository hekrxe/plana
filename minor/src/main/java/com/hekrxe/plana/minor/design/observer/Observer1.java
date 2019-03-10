package com.hekrxe.plana.minor.design.observer;

/**
 * @author tanhuayou on 2019/02/25
 */
public class Observer1 implements Observer {
    @Override
    public void update() {
        System.out.println("Observer1 update");
    }

    @Override
    public String name() {
        return "Observer1";
    }
}
