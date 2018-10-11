package com.hekrxe.plana.minor.algorithm.sword;

/**
 * 在一个二维数组中，每一行都是按照从左到右递增的顺序排列，每一列都是按照从上到下递增的顺序排列。
 * 判断这个数组中是否存在一个数。
 * 如：
 * 1，2，3
 * 4，5，6
 * 7，8，9
 * 是否包含5
 * <p>
 * User: tanhuayou
 * Date: 2018/9/3
 */
public class FindInBinaryArray {

    /**
     * 确定范围的过程
     */
    public static boolean contains(int[][] arr, int expected) {
        boolean find = false;
        int rows;
        if (null == arr ||
                (rows = arr.length) <= 0 ||
                arr[0].length <= 0) {
            return false;
        }
        int row = 0;
        int col = rows - 1;

        while (row < rows && col >= 0 && row >= 0) {
            int ele = arr[row][col];
            if (expected == ele) {
                find = true;
                break;
            } else if (ele < expected) {
                row++;
            } else {
                col--;
            }
        }

        return find;
    }

    public static void main(String[] args) {
        int arr[][] = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(contains(arr, 4));
    }

}
