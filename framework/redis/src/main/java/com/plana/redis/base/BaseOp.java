package com.plana.redis.base;

import com.plana.redis.AppConfiguration;
import io.lettuce.core.protocol.CommandType;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

/**
 * @see CommandType
 * User: tanhuayou
 * Date: 2018/9/5
 */
public class BaseOp {


    /**
     * 动态字符串
     * 当字符串长度小于 1M 时，扩容都是加倍现有的空间，
     * 如果超过 1M，扩容时一次只会多扩 1M 的空间。需要注意的是字符串最大长度为 512M。
     */
    private static void stringOp() {
        //     APPEND, GET, GETRANGE, GETSET, MGET, MSET, MSETNX, SET, SETEX, PSETEX, SETNX, SETRANGE, STRLEN

        ValueOperations<String, String> template = AppConfiguration.getStringRedisTemplate().opsForValue();
//
//        String key1 = "key1";
//        String key2 = "key2";
//        String key3 = "key3";
//        String key4 = "key4";
//
//        template.set(key1, "v1");
//        System.out.println("set/get: " + template.get(key1));
//        template.append(key1, "appendVal");
//        System.out.println("append/get: " + template.get(key1));
//
//        template.append(key2, "appendVal2");
//        System.out.println("append/get: " + template.get(key2));
//
//        template.set(key3, "v3");
//
//        // 当第一次设置了key的过期时间后 再次更新时 其过期时间会失效（如果第二次没有设置得话）
//        template.set(key4, "v4", 1, TimeUnit.DAYS);
//        Long expire = template.getOperations().getExpire(key4);
//        System.out.println(key4 + " expire: " + expire);
//        template.set(key4, "v4");
//        expire = template.getOperations().getExpire(key4);
//        System.out.println(key4 + " expire: " + expire);
//
//
//        List<String> values = template.multiGet(Arrays.asList(key1, key2, key3, key4));
//        System.out.println(Arrays.toString(values.toArray()));
    }

    /**
     * 列表 ： 理解起来可以认为是一个双向链表
     * 首先在列表元素较少的情况下会使用一块连续的内存存储，这个结构是 ziplist，也即是压缩列表。
     * 它将所有的元素紧挨着一起存储，分配的是一块连续的内存。
     * <p>
     * 当数据量比较多的时候才会改成 quicklist。因为普通的链表需要的附加指针空间太大，会比较浪费空间，而且会加重内存的碎片化。
     * 所以 Redis 将链表 和 ziplist 结合起来组成了 quicklist。也就是将多个 ziplist 使用双向指针串起来使用。
     * zip1 <-> zip2 <-> zip3
     */
    private static void listOp() {
        ListOperations<String, String> list = AppConfiguration.getStringRedisTemplate().opsForList();
    }

    /**
     * 数组 + 链表 实现
     * <p>
     * 当字典超过一定值时： Redis 进行 【渐进式rehash】
     * <p>
     * 渐进式 rehash 会在 rehash 的同时，保留新旧两个 hash 结构，查询时会同时查询两个 hash 结构，
     * 然后在后续的定时任务中以及 hash 操作指令中，循序渐进地将旧 hash 的内容一点点迁移到新的 hash 结构中。
     */
    private static void hashOp() {
        HashOperations<String, String, String> hash = AppConfiguration.getStringRedisTemplate().opsForHash();
    }

    private static void zsetOp() {
        ZSetOperations<String, String> zset = AppConfiguration.getStringRedisTemplate().opsForZSet();
    }


    public static void main(String[] args) {
        stringOp();

    }
}
