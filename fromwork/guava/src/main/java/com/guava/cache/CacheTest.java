package com.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * User: tanhuayou
 * Date: 2018/6/21
 */
public class CacheTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CacheLoader<String, String> cacheLoader = new CacheLoader<String, String>() {
            @Override
            public String load(String key) throws InterruptedException {
                System.out.println("load key=" + key);
                Thread.sleep(100);
                // 返回值不能为 null
                return String.valueOf(System.currentTimeMillis());
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .build(cacheLoader);

        // 不存在则调用 load并将新值缓存起来
        System.out.println(loadingCache.get("abc"));
        Thread.sleep(1100);
        System.out.println(loadingCache.get("abc"));
        // 不存在返回 null
        System.out.println(loadingCache.getIfPresent("bcd"));

        loadingCache.put("cde", "hi");
        System.out.println(loadingCache.get("cde"));

    }
}
