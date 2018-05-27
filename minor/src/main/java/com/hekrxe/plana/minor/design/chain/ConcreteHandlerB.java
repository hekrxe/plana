package com.hekrxe.plana.minor.design.chain;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class ConcreteHandlerB extends AbstractHandler {

    @Override
    public void doSomething() {
        System.out.println("ConcreteHandlerB");
        AbstractHandler next = getNext();
        if (null != next) {
            next.doSomething();
        }
    }
}
