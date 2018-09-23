package com.plana.redis.base;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: tanhuayou
 * Date: 2018/9/10
 */
public class FunnelLimiter {
    private static final Map<String, Funnel> LIMITER = new ConcurrentHashMap<>();

    private static class Funnel {
        /**
         * 容量
         */
        private int capacity;
        /**
         * 剩余量
         */
        private int unusedCapacity;
        /**
         * 流水速率
         */
        private float leakRate;
        /**
         * 最后一次加水时间
         */
        private long lastWateringTime;

        Funnel(int capacity, float leakRate) {
            this.capacity = capacity;
            this.leakRate = leakRate;
            this.unusedCapacity = capacity;
            this.lastWateringTime = System.currentTimeMillis();
        }

        /**
         * 计算自上一次加水后到现在 还能加多少容量
         */
        private void mk() {
            long nowTs = System.currentTimeMillis();
            long interval = nowTs - lastWateringTime;
            int newCapacity = (int) (interval * leakRate);
            System.out.println("capacity=" + capacity + ",leakRate=" + leakRate + ",unusedCapacity=" + unusedCapacity + "interval=" + interval + ",newCapacity=" + newCapacity);
            if (newCapacity < 0) {
                unusedCapacity = capacity;
                lastWateringTime = nowTs;
            } else if (newCapacity > 0) {
                unusedCapacity += newCapacity;
                if (unusedCapacity > capacity) {
                    unusedCapacity = capacity;
                }
                lastWateringTime = nowTs;
            }
        }

        boolean watering(int quota) {
            mk();
            // 剩余容量是否够本次申请
            if (unusedCapacity >= quota) {
                unusedCapacity -= quota;
                return true;
            }
            return false;
        }
    }

    public static boolean isActionAllowed(String userId, String actionKey, int capacity, float rate) {
        String key = userId + "@" + actionKey;
        Funnel funnel = LIMITER.get(key);
        if (null == funnel) {
            funnel = new Funnel(capacity, rate);
            LIMITER.put(key, funnel);
        }
        return funnel.watering(1);
    }

    private static void eat() {
        String userId = "thy";
        String action = "eat";
        int capacity = 5; // 一天最多吃5次饭
        float rate = 5.0F / (1000 * 60 * 60 * 24); //
        System.out.println(rate);

        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + "  " + isActionAllowed(userId, action, capacity, rate));
        }
    }

    private static void drinkWater() {
        String userId = "thy";
        String action = "drinking";
        int capacity = 3;
        float rate = 2.0F / (3000);// 每三秒最多喝两次水

        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + "  " + isActionAllowed(userId, action, capacity, rate));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        drinkWater();
    }
}
