package com.hekrxe.plana.minor;

import java.lang.reflect.Field;

/**
 * User: tanhuayou
 * Date: 2018/4/25
 */
public class UnsafeInstance {
    private static sun.misc.Unsafe unsafe;

    static {
        for (Field field : sun.misc.Unsafe.class.getDeclaredFields()) {
            if (field.getType() == sun.misc.Unsafe.class) {
                field.setAccessible(true);
                try {
                    unsafe = (sun.misc.Unsafe) field.get(null);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    public static sun.misc.Unsafe get() {
        return unsafe;
    }
}
