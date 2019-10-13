package com.hekrxe.plana.minor;

import java.nio.charset.StandardCharsets;

/**
 * Created by hztanhuayou on 2017/11/13
 */
public class Main {
    public static void main(String[] args) {
        String str = "11你🔢";
        System.out.println(str.length());
        System.out.println(str.getBytes(StandardCharsets.UTF_8).length);
    }
}
