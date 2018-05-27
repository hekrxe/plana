package com.hekrxe.plana.minor.design.decorator;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void doSomething() {
        before();
        component.doSomething();
        after();
    }

    public void before() {
    }

    public void after() {
    }
}
