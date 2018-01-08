package com.hekrxe.plana.minor.design.builder;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Client {
    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        director.construct();
        Product product = builder.build();
        System.out.println(product);
    }
}
