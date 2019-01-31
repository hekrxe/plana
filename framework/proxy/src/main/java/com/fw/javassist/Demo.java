package com.fw.javassist;

/**
 * Created by tanhuayou on 2019/01/18
 */
public class Demo {

    public int add(int a, int b) {
        System.out.println(a + "+" + b);
        return a + b;
    }

    public void time() {
        try {
            System.out.println("Time...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
