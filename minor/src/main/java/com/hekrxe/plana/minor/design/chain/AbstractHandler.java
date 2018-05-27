package com.hekrxe.plana.minor.design.chain;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public abstract class AbstractHandler {
    private AbstractHandler next;

    public AbstractHandler getNext() {
        return next;
    }

    public AbstractHandler setNext(AbstractHandler next) {
        this.next = next;
        return this;
    }

    public abstract void doSomething();
}
