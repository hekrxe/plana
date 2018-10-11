package com.hekrxe.plana.minor.algorithm.sword;

/**
 * User: tanhuayou
 * Date: 2018/9/4
 */
public class Fibonacci {

    public static long fnRec(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        return fnRec(n - 1) + fnRec(n - 2);
    }

    public static long fn(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        long n2 = 0;
        long n1 = 1;
        long n3;
        for (int i = 2; i <= n; ++i) {
            n3 = n1;
            n1 = n1 + n2;
            n2 = n3;
        }

        return n1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; ++i) {
            System.out.println(fn(i));
        }
    }

}
