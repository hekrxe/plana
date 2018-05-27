package com.hekrxe.plana.minor.juc;

import com.hekrxe.plana.minor.UnsafeInstance;

import java.util.concurrent.ConcurrentHashMap;

/**
 * User: tanhuayou
 * Date: 2018/4/25
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {


        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("12", "222");
        map.get("aaa");
        System.out.println(map);
    }

    private static void arrayTest() {
        Integer[] arr = new Integer[10];
        // 出去对象头后（header）,正在存放数据元素的偏移量
        // h:xxxxxxx
        int base = UnsafeInstance.get().arrayBaseOffset(arr.getClass());
        // 一个元素的大小
        // x的大小
        int scale = UnsafeInstance.get().arrayIndexScale(arr.getClass());
        int shift = 31 - Integer.numberOfLeadingZeros(scale);
        System.out.println("base=" + base);
        System.out.println("scale=" + scale);
        System.out.println("shift=" + shift);
        int i = 9;
        UnsafeInstance.get().compareAndSwapObject(arr, ((long) i * scale) + base, null, 100);
        System.out.println(arr[i]);
    }
}
