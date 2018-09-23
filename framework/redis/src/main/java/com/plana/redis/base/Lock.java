package com.plana.redis.base;

import com.plana.redis.AppConfiguration;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis 实现分布式锁，其原理是在Redis里设置一个key,设置成功的那个线程获得锁，否则获取锁失败。
 * SETNXEX
 * <p>
 * User: tanhuayou
 * Date: 2018/9/5
 */
public class Lock {

    public static boolean lock(String key, String value, int expireSecond) {
        ValueOperations<String, String> template = AppConfiguration.getStringRedisTemplate().opsForValue();

        RedisSerializer keySerializer = template.getOperations().getKeySerializer();
        RedisSerializer valueSerializer = template.getOperations().getValueSerializer();

        return template.getOperations().execute((RedisCallback<Boolean>) connection ->
                connection.set(keySerializer.serialize(key), valueSerializer.serialize(value),
                        Expiration.seconds(expireSecond), RedisStringCommands.SetOption.SET_IF_ABSENT)
        );
    }

    public static boolean unlock(String key, String expected) {
        ValueOperations<String, String> template = AppConfiguration.getStringRedisTemplate().opsForValue();
        String origin = template.get(key);
        return expected.equals(origin) && AppConfiguration.getStringRedisTemplate().delete(key);
    }

    public static void main(String[] args) throws InterruptedException {
        String key = "lock.test";
        for (int i = 0; i < 10; ++i) {
            System.out.println("====" + lock(key, "1", 4));
            Thread.sleep(500);
        }
        System.out.println(unlock(key, "1"));
    }

}
