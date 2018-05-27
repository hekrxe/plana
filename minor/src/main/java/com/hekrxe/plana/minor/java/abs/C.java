package com.hekrxe.plana.minor.java.abs;

/**
 * User: tanhuayou
 * Date: 2018/4/16
 */
public class C implements A, B {
    @Override
    public void hi() {
        System.out.println("只需要实现一个就可以了");
    }

    public static void main(String[] args) {
        C c = new C();
        c.hi();
        A a = c;
        a.hi();
        B b = c;
        b.hi();
    }
}
