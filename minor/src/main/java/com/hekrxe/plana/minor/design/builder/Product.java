package com.hekrxe.plana.minor.design.builder;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Product {
    private String part1;
    private String part2;

    public String getPart1() {
        return part1;
    }

    public Product setPart1(String part1) {
        this.part1 = part1;
        return this;
    }

    public String getPart2() {
        return part2;
    }

    public Product setPart2(String part2) {
        this.part2 = part2;
        return this;
    }

    @Override
    public String toString() {
        return "Product{" +
                "part1='" + part1 + '\'' +
                ", part2='" + part2 + '\'' +
                '}';
    }
}
