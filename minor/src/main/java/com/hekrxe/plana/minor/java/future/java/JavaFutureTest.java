package com.hekrxe.plana.minor.java.future.java;

import java.util.concurrent.*;

/**
 * User: tanhuayou
 * Date: 2018/6/19
 */
public class JavaFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), r -> {
            Thread thread = new Thread(r);
            thread.setName("Demo");
            return thread;
        });
        Callable<Integer> task = () -> {
            Thread.sleep(15000);
            return 1;
        };

        // 其实现是：源码一看大致都明白了。。
        // 1, 将 callable 封装为一个 RunnableFuture 对象，RunnableFuture 是Future的一个子接口且也是 runnable的子接口
        // 2, 因此当线程执行runnable的 run 方法的时候 其实执行的是 RunnableFuture的run方法
        // 3, 在 RunnableFuture的 run 方法内真正执行的是 Callable的 call方法
        // 4, 当call方法执行完之后返回 RunnableFuture的run 方法，此时将 call方法的返回值保存起来
        // 5, 以上所有实现过程都会设置 一个 FutureTask(RunnableFuture的一个实现类)的state状态位。
        // 6, 当其他线程调用 Future的 get() 方法时，根据 state 状态来判断任务是否执行完毕来是否挂起(park)当前调用线程，如果挂起的话会将当前线程加入到一个等待队列上去
        // 7, 若 call 执行完之后 已经有线程在等待队列被挂起了,那么执行唤醒操作(unpark), 那么那些等待的线程就从等待处继续执行以获得最终结果。
        //
        // 优点：使任务有返回值可取(相对runnable)来说
        // 缺点：其它线程会被挂起(阻塞)
        Future<Integer> future = executor.submit(task);
        System.out.println("wait");
        System.out.println(future.get());
        executor.shutdown();
    }
}
