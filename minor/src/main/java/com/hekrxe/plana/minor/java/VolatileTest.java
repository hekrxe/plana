package com.hekrxe.plana.minor.java;

/**
 * 1. 对volatile变量的写会立即刷新到主存
 * 2. 对volatile变量的读会读主存中的新值
 * 3. 对一个volatile变量的写操作，只有所有步骤完成，才能被其它线程读取到。
 * 4. 多个线程对volatile变量的写操作本质上是有先后顺序的。也就是说并发写没有问题。
 * 5. 写入操作将会变成一个原子操作，没有初始化完，就不会被刷新到主存中
 * User: tanhuayou
 * Date: 2018/4/9
 */
public class VolatileTest {

    private static class VolatileTask implements Runnable {
        //        boolean running = true;
        volatile boolean running = true;

        int i = 0;

        @Override
        public void run() {
            while (running) {
                i++;
            }
            System.out.println("Task done");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        VolatileTask task = new VolatileTask();
        Thread thread = new Thread(task);
        thread.start();
        Thread.sleep(100);
        task.running = false;
        Thread.sleep(1000);
        System.out.println(task.i);
    }
}
