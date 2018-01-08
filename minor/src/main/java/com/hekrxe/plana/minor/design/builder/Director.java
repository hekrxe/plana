package com.hekrxe.plana.minor.design.builder;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildPart1();
        builder.buildPart2();
    }
}
