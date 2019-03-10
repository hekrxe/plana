package com.hekrxe.plana.minor.design.observer;

/**
 * @author tanhuayou on 2019/02/25
 */
public class MySubject extends AbstractSubject {
    @Override
    public void operation() {
        System.out.println("MySubject.operation");
        notifyObserver();
    }
}
