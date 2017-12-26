package com.hekrxe.plana.minor.spi;

import java.util.ServiceLoader;

/**
 * @author hztanhuayou
 * @date 2017/12/19
 */
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        for (Search next : load) {
            System.out.println(next.search());
        }
    }
}
