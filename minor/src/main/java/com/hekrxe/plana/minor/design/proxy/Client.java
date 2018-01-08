package com.hekrxe.plana.minor.design.proxy;

/**
 * @author hztanhuayou
 * @date 2018/1/6
 */
public class Client {
    public static void main(String[] args) {
        Target target = new Target();
        Action proxy = new Proxy(target);
        proxy.action();
    }
}
