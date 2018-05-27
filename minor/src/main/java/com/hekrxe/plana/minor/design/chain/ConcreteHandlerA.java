package com.hekrxe.plana.minor.design.chain;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class ConcreteHandlerA extends AbstractHandler {

    @Override
    public void doSomething() {
        System.out.println("ConcreteHandlerA");
        AbstractHandler next = getNext();
        if (null != next) {
            next.doSomething();
        }
    }
}
