package com.plana.redis.base;

import com.plana.redis.AppConfiguration;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.concurrent.TimeUnit;

/**
 * User: tanhuayou
 * Date: 2018/9/10
 */
public class RateLimiter {


    /**
     * 允许某个用户 userId 在 指定时间period 范围内最多执行某个动作 actionKey max次。
     */
    public static boolean isActionAllowed(String userId, String actionKey, long periodMills, int max) {
        ZSetOperations<String, Long> zset = AppConfiguration.getNumberTemplate().opsForZSet();
        String key = userId + "@" + actionKey;
        long nowTs = System.currentTimeMillis();

        // k v s
        zset.add(key, nowTs, nowTs);
        // 删除分数在 0 ~ (nowTs - period) ，言外之意就是 剩下的是 过去period秒到现在的数据
        zset.removeRangeByScore(key, 0, nowTs - periodMills);
        // 有效存在的次数
        Long count = zset.count(key, nowTs - periodMills, nowTs);
        AppConfiguration.getNumberTemplate().expire(key, 20, TimeUnit.SECONDS);
        System.out.println(count);
        return count < max;
    }

    public static void main(String[] args) throws InterruptedException {
        String userId = "thy";
        String action = "coding";
        long period = 1000 * 2;
        int max = 3;

        for (int i = 0; i < 10; ++i) {
            System.out.println(isActionAllowed(userId, action, period, max));
            Thread.sleep(520);
        }
    }

}
