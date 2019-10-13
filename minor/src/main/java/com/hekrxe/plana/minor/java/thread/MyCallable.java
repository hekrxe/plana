package com.hekrxe.plana.minor.java.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author tanhuayou on 2019/05/23
 */
public class MyCallable implements Callable<String> {
    @Override
    public String call() throws InterruptedException {
        Thread.sleep(1000);
        return "call result";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(task);
        thread.start();

        System.out.println(task.get());
    }
}
