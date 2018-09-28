package com.hekrxe.plana.minor.java;

/**
 * User: tanhuayou
 * Date: 2018/6/20
 */
public class FinallyTest {
    public static void main(String[] args) {
        System.out.println(test());
    }

    private static int test() {
        int i = 0;
        try {
            i = i + 1;
            return i;
        } finally {
            return i + 1;
        }
    }
}
