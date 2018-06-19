package com.hekrxe.plana.minor.java.future.myfuture;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: tanhuayou
 * Date: 2018/6/20
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 5, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), r -> {
            Thread thread = new Thread(r);
            thread.setName("MyFuture");
            return thread;
        });

        FutureTask<Integer> task = new FutureTask<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(1000);
                return 520;
            }
        };

        task.addListener(System.out::println);
        task.addListener(result -> System.out.println("hi " + result));
        task.addListener(result -> System.out.println(Thread.currentThread().getName() + result));

        executor.execute(task);

        Thread.sleep(2000);

        task.addListener(result -> System.out.println(Thread.currentThread().getName() + result));
        task.addListener(result -> System.out.println(Thread.currentThread().getName() + result));

        Thread.sleep(1000);

        executor.shutdown();
    }
}
