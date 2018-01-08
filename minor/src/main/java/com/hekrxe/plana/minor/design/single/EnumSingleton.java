package com.hekrxe.plana.minor.design.single;

/**
 * 使用枚举来实现单实例控制会更加简洁，而且无偿地提供了序列化机制，
 * 并由JVM从根本上提供保障，绝对防止多次实例化，是更简洁、高效、安全的实现单例的方式。
 *
 * @author hztanhuayou
 * @date 2018/1/6
 */
public enum EnumSingleton {
    /**
     * 唯一一个实例
     */
    INSTANCE() {
        int a = 2;

        /**
         * 实现抽象方法
         */
        @Override
        public void display() {
            System.out.println("EnumSingleton" + a);
        }
    };

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }

    /**
     * 这里是抽象方法
     */
    public void display() {
        throw new UnsupportedOperationException("Abs method do not invoke");
    }

    public static void main(String[] args) {
        getInstance().display();
    }
}
