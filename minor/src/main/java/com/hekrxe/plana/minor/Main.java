package com.hekrxe.plana.minor;

/**
 * Created by hztanhuayou on 2017/11/13
 */
public class Main {
    public static void main(String[] args) {
        short a = 32767;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(Integer.toBinaryString(~(1 << 14)));
        System.out.println(a & (~(1 << 14)));
    }
}
