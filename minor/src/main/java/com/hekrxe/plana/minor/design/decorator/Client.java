package com.hekrxe.plana.minor.design.decorator;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator(new ConcreteComponent());
        component.doSomething();
    }
}
