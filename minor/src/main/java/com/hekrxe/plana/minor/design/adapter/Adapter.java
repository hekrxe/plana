package com.hekrxe.plana.minor.design.adapter;

/**
 * 也可以组合Adaptee来达到目的
 *
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Adapter extends Adptee implements Target {
    /**
     * 因为 Adptee  没有这个类
     * 所以在这补上
     */
    @Override
    public void operationTwo() {
        System.out.println(getClass().getSimpleName());
    }
}
