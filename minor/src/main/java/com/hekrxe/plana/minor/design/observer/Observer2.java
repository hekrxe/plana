package com.hekrxe.plana.minor.design.observer;

/**
 * @author tanhuayou on 2019/02/25
 */
public class Observer2 implements Observer {
    @Override
    public void update() {
        System.out.println("Observer2 update");
    }

    @Override
    public String name() {
        return "Observer2";
    }
}
