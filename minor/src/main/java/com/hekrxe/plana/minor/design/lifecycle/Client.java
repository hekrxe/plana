package com.hekrxe.plana.minor.design.lifecycle;

/**
 * User: tanhuayou
 * Date: 2018/5/27
 */
public class Client {
    public static void main(String[] args) {
        CommonListener listener = new CommonListener();

        Context context = new Context();
        context.addListener(listener);
        context.start();
        context.end();

        Engine engine = new Engine();
        engine.addListener(listener);
        engine.addListener((event) -> {
            System.out.println(event.getTrigger());
            System.out.println(event.getState());
        });
        engine.start();
        engine.end();
    }
}
