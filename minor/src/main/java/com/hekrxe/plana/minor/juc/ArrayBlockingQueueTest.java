package com.hekrxe.plana.minor.juc;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * User: tanhuayou
 * Date: 2018/6/26
 */
public class ArrayBlockingQueueTest {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        for (int i = 1; i < 14; ++i) {
            queue.add(i);
        }

        for (Integer e : queue) {
            System.out.println(e);
        }

    }
}
