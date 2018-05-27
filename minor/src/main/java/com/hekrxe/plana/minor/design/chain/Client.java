package com.hekrxe.plana.minor.design.chain;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class Client {
    public static void main(String[] args) {
        ConcreteHandlerA handlerA = new ConcreteHandlerA();
        ConcreteHandlerB handlerB = new ConcreteHandlerB();
        handlerA.setNext(handlerB);

        handlerA.doSomething();
    }
}
