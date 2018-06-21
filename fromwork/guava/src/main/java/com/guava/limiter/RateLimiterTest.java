package com.guava.limiter;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: tanhuayou
 * Date: 2018/6/21
 */
public class RateLimiterTest {
    public static void main(String[] args) throws InterruptedException {
        simple();
    }

    private static void simple() throws InterruptedException {
        // 不希望每秒超过两个任务执行
        // 保证平均每秒不超过permitsPerSecond个令牌被获取
        // 当请求的速度超过了permitsPerSecond，保证每秒只处理permitsPerSecond个请求
        // 当这个RateLimiter使用不足(即请求到来速度小于permitsPerSecond)，会囤积最多permitsPerSecond个令牌
        RateLimiter rateLimiter = RateLimiter.create(5.0);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 30, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<>(), r -> {
            Thread thread = new Thread(r);
            thread.setName("RateLimiterTest");
            return thread;
        });

        for (int i = 0; i < 1000; ++i) {
//            // tryAcquire 会立即返回
//            if (rateLimiter.tryAcquire()) {
//                // 令牌获取成功
//            }

            // 阻塞 直到获取成功
            rateLimiter.acquire();
            executor.execute(() -> System.out.println(System.currentTimeMillis()));
        }

        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
