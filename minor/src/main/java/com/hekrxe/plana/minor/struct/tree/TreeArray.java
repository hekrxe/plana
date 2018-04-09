package com.hekrxe.plana.minor.struct.tree;

/**
 * @author hztanhuayou
 * @date 2017/11/24
 */
public class TreeArray {

    /**
     * 补码从低位向高位找到的第一个数字1后的数值.
     * 一个数的二进制末尾0的个数,在前面再加一个1
     *
     * @param num number
     * @return num & -num
     */
    private static int lowBit(int num) {
        return num & ((~num) + 1);
    }

    public static void main(String[] args) {
        for (int i = -32; i <= 32; ++i) {
            System.out.println(i + "\t" + Integer.toBinaryString(lowBit(i)));
        }

    }
}
