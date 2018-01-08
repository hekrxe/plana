package com.hekrxe.plana.minor.design.single;

/**
 * 懒汉式: 要使用对象实例的时候才会创建
 *
 * @author hztanhuayou
 * @date 2018/1/6
 */
public final class LazySingleton {
    private static volatile LazySingleton INSTANCE = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (null == INSTANCE) {
            synchronized (LazySingleton.class) {
                if (null == INSTANCE) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }

    public void display() {
        System.out.println("LazySingleton");
    }

    public static void main(String[] args) {
        getInstance().display();
    }
}
