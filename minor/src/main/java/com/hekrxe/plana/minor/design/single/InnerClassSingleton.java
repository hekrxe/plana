package com.hekrxe.plana.minor.design.single;

/**
 * 类级内部类相当于其外部类的成员，只有在第一次被使用的时候才被会装载
 *
 * @author hztanhuayou
 * @date 2018/1/6
 */
public final class InnerClassSingleton {

    private InnerClassSingleton() {
    }

    private static class InnerInstance {
        /**
         * 静态域 静态块 JVM会保证线程间的同步.
         */
        private static InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    public void display() {
        System.out.println(this.getClass().getSimpleName());
    }

    public static InnerClassSingleton getInstance() {
        return InnerInstance.INSTANCE;
    }

    public static void main(String[] args) {
        getInstance().display();
    }

}
