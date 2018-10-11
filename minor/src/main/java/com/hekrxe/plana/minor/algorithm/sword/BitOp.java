package com.hekrxe.plana.minor.algorithm.sword;

/**
 * User: tanhuayou
 * Date: 2018/9/4
 */
public class BitOp {

    public static int bitCount(long number) {
        int count = 0;

        long base = 1;

        while (base != 0) {
            if ((number & base) != 0) {
                count++;
            }
            base <<= 1;
        }

        return count;
    }

    public static int bitCount2(long number) {
        int count = 0;

        while (number != 0) {
            // 消除最右边的1
            number = number & (number - 1);
            count++;
        }

        return count;
    }

    // 异或 = 相同位不同的数被记录下来

    public static int lowZeroCount(long number) {
        if (0 == number) return 0;

        long base = 1;

        int count = 0;
        while (base != 0) {
            if ((number & base) == base) {
                break;
            }
            count++;
            base <<= 1;
        }

        return count;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            System.out.println(Long.toBinaryString(-i) + "   " + lowZeroCount(-i));
        }
        System.out.println(Long.toBinaryString(Long.MIN_VALUE) + "  " + lowZeroCount(Long.MIN_VALUE));
    }
}
