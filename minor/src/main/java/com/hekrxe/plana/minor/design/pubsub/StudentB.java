package com.hekrxe.plana.minor.design.pubsub;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class StudentB implements Observer {
    @Override
    public void update() {
        System.out.println("B update");
    }
}
