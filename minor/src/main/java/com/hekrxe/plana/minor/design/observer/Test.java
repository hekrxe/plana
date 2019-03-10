package com.hekrxe.plana.minor.design.observer;

/**
 * @author tanhuayou on 2019/02/25
 */
public class Test {
    public static void main(String[] args) {
        Subject subject = new MySubject();

        subject.addObserver(new Observer1());
        subject.addObserver(new Observer2());
        subject.addObserver(new Observer() {
            @Override
            public void update() {
                System.out.println("Test.update");
            }

            @Override
            public String name() {
                return "Test";
            }
        });

        subject.operation();

    }
}
