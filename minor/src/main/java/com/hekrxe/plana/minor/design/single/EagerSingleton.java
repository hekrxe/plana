package com.hekrxe.plana.minor.design.single;

/**
 * 饿汉式: 在装载类的时候就创建对象实例
 *
 * @author hztanhuayou
 * @date 2018/1/6
 */
public final class EagerSingleton {
    private static EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {
    }

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }

    public void display() {
        System.out.println("EagerSingleton");
    }

    public static void main(String[] args) {
        getInstance().display();
    }
}
