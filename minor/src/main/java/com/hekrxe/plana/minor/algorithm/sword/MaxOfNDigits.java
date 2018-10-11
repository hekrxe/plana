package com.hekrxe.plana.minor.algorithm.sword;


/**
 * User: tanhuayou
 * Date: 2018/9/4
 */
public class MaxOfNDigits {


    public static void printRec(char[] buf, int index) {
        if (index == buf.length) {
            System.out.println(new String(buf));
            return;
        }

        for (int i = 0; i < 10; ++i) {
            buf[index] = (char) (i + '0');
            printRec(buf, index + 1);
        }
    }

    // 全排列
    public static void printFullPermutation(char[] src, int first) {
        if (first == src.length) {
            System.out.println(new String(src));
            return;
        }

        for (int i = first; i < src.length; i++) {

            char tmp = src[i];
            src[i] = src[first];
            src[first] = tmp;

            printFullPermutation(src, first + 1);

            tmp = src[i];
            src[i] = src[first];
            src[first] = tmp;
        }

    }

    public static void main(String[] args) {
        char[] src = {'a', 'b', 'c'};

        printFullPermutation(src, 0);
    }
}
