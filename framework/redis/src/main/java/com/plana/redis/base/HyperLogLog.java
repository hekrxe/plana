package com.plana.redis.base;

import com.plana.redis.AppConfiguration;
import org.springframework.data.redis.core.HyperLogLogOperations;

/**
 * User: tanhuayou
 * Date: 2018/9/10
 */
public class HyperLogLog {



    public static void main(String[] args) {
        HyperLogLogOperations<String, String> logLog =
                AppConfiguration.getStringRedisTemplate().opsForHyperLogLog();
        String key1 = "logKey1";
        logLog.add(key1, "a", "b", "c");
        String key2 = "logKey2";
        logLog.add(key2, "b", "c", "d");

        String key3 = "logKey3";
        logLog.add(key3, "c", "d", "e");

        String key4 = "logKey4";
        logLog.add(key4, "a", "b", "c");


        System.out.println(logLog.size(key1));
        System.out.println(logLog.size(key2));
        System.out.println(logLog.size(key3));
        System.out.println(logLog.size(key4));

        String unionKey = "uni";
        Long union = logLog.union(unionKey, key1, key2, key3, key4);
        System.out.println("union=" + union);

        System.out.println(logLog.size(unionKey));

    }
}
