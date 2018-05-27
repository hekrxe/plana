package com.hekrxe.plana.minor.design.decorator;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void before() {
        System.out.println("准备。。");
    }

    @Override
    public void after() {
        System.out.println("结束了");
    }
}
