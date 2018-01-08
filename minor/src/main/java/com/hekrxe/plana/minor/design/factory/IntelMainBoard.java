package com.hekrxe.plana.minor.design.factory;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class IntelMainBoard implements MainBoard {
    @Override
    public void installCPU(CPU cpu) {
        System.out.println(getClass().getSimpleName());
    }
}
