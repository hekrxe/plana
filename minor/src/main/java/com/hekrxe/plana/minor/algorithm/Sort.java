package com.hekrxe.plana.minor.algorithm;

import java.util.Random;

/**
 * @author hztanhuayou
 * @date 2017/12/26
 */
public class Sort {

    /**
     * 分成两部分,前部分已经排好序,后半部分是待排序的,那么只需依次将后半部分数据找到正确的位置即可
     *
     * @param array 待排序数组
     */
    public static void insertSort(int[] array) {
        if (null == array || array.length <= 0) {
            return;
        }
        int i;
        // 前j-1个数据已经排好序了, 从第j个数据开始排序
        int j = 1;
        while (j < array.length) {
            i = j++;
            int tmp = array[i];
            while (i > 0 && array[i - 1] > tmp) {
                array[i] = array[i - 1];
                i--;
            }
            array[i] = tmp;
        }
    }

    /**
     * 希尔排序
     *
     * @param array array
     */
    public static void shellInsertSort(int[] array) {
        if (null == array || array.length <= 0) {
            return;
        }
        int countOfGroup = array.length >>> 1;
        while (countOfGroup >= 1) {
            // i 表示某组的第一个元素
            for (int i = 0; i < countOfGroup; ++i) {

                // 直接插入排序
                // 认为每组的前i个元素已经有序,从该组的第二个元素(i + countOfGroup)开始排序
                int j = i + countOfGroup;
                while (j < array.length) {
                    int k = j;
                    int tmp = array[k];
                    while (k > i && array[k - countOfGroup] > tmp) {
                        array[k] = array[k - countOfGroup];
                        k -= countOfGroup;
                    }
                    array[k] = tmp;
                    j += countOfGroup;
                }

            }
            countOfGroup >>>= 1;
        }
    }

    private static void display(int[] array) {
        for (int e : array) {
            System.out.print(e + ",\t");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        int size = 13;
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; ++i) {
            array[i] = Math.abs(random.nextInt() % 100);
        }
        display(array);
        long now = System.currentTimeMillis();
        shellInsertSort(array);
        System.out.println(System.currentTimeMillis() - now);
        display(array);

    }
}
