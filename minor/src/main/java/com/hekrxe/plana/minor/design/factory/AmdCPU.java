package com.hekrxe.plana.minor.design.factory;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class AmdCPU implements CPU {
    @Override
    public void calculate() {
        System.out.println(getClass().getSimpleName());
    }
}
