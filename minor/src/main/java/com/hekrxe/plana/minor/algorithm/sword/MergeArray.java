package com.hekrxe.plana.minor.algorithm.sword;

import java.util.Arrays;

/**
 * 给定两个有序数组，合并后保持顺序
 * User: tanhuayou
 * Date: 2018/9/3
 */
public class MergeArray {

    public static int[] merge(int[] array1, int[] array2) {
        if (null == array1 || null == array2) {
            return null == array1 ? array2 : array1;
        }
        int length1 = array1.length;
        int length2 = array2.length;
        if (length1 <= 0 || length2 <= 0) {
            return length1 <= 0 ? array2 : array1;
        }

        int[] newArray = new int[length1 + length2];
        int index = newArray.length - 1;

        length1--;
        length2--;

        while (length1 >= 0 && length2 >= 0) {

            int ele1 = array1[length1];
            int ele2 = array2[length2];

            if (ele1 > ele2) {
                newArray[index--] = ele1;
                length1--;
            } else if (ele1 < ele2) {
                newArray[index--] = ele2;
                length2--;
            } else {
                newArray[index--] = ele1;
                newArray[index--] = ele2;

                length1--;
                length2--;
            }
        }

        while (length1 >= 0) {
            newArray[index--] = array1[length1--];
        }

        while (length2 >= 0) {
            newArray[index--] = array2[length2--];
        }


        return newArray;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7};
        int[] arr2 = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        System.out.println(Arrays.toString(merge(arr1, arr2)));
    }
}
