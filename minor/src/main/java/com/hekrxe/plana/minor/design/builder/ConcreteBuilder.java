package com.hekrxe.plana.minor.design.builder;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class ConcreteBuilder implements Builder {
    private Product product = new Product();

    @Override
    public void buildPart1() {
        product.setPart1("1");
    }

    @Override
    public void buildPart2() {
        product.setPart2("2");
    }

    @Override
    public Product build() {
        return product;
    }
}
