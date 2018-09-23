package com.plana.redis.base;

import com.plana.redis.AppConfiguration;
import org.springframework.data.redis.core.ListOperations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: tanhuayou
 * Date: 2018/9/6
 */
public class MakeMQ {
    private static final String QUEUE_TOPIC = "queue_topic";
    private static final ListOperations<String, String> TEMPLATE = AppConfiguration.getStringRedisTemplate().opsForList();


    public static class Producer implements Runnable {
        private String data;

        public Producer(String data) {
            this.data = data;
        }

        @Override
        public void run() {
            TEMPLATE.leftPush(QUEUE_TOPIC, data);
        }
    }

    public static class Consumer implements Runnable {
        @Override
        public void run() {

            String data = TEMPLATE.rightPop(QUEUE_TOPIC, 3, TimeUnit.SECONDS);
            System.out.println("DATA: " + data);
            data = TEMPLATE.rightPop(QUEUE_TOPIC, 3, TimeUnit.SECONDS);
            System.out.println("DATA: " + data);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);


        Thread.sleep(1500);
        for (int i = 0; i < 2; ++i) {
            executorService.execute(new Producer("Timi-" + i));
        }

        Thread.sleep(1500);

        executorService.execute(new Consumer());

        executorService.awaitTermination(15, TimeUnit.SECONDS);
        executorService.shutdownNow();
    }
}
