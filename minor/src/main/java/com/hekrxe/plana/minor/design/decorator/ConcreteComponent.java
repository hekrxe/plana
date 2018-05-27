package com.hekrxe.plana.minor.design.decorator;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomething() {
        System.out.println("ConcreteComponent doSomething");
    }
}
