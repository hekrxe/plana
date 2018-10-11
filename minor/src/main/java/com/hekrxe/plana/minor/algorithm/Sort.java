package com.hekrxe.plana.minor.algorithm;

import java.util.Random;

/**
 * @author hztanhuayou
 * @date 2017/12/26
 */
public class Sort {


    private static void display(int[] array) {
        for (int e : array) {
            System.out.print(e + "\t,");
        }
        System.out.println();
    }

    private static void display(int[] array, int i, int j) {
        for (int c = i; c <= j; ++c) {
            System.out.print(array[c] + "\t,");
        }
        System.out.println();
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    /**
     * 分成两部分,前部分已经排好序,后半部分是待排序的,那么只需依次将后半部分数据找到正确的位置即可
     *
     * @param array 待排序数组
     */
    public static void insertSort(int[] array) {
        if (null == array || array.length <= 1) {
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
        if (null == array || array.length <= 1) {
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

    public static void bubbleSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length - 1; ++i) {
            for (int j = 0; j < array.length - i - 1; ++j) {
                if (array[j] > array[j + 1]) {
                    swap(array, j + 1, j);
                }
            }
        }
    }

    private static int partition(int[] array, int left, int right) {
        // mid 将数组分成了两个分区
        // mid后的都是大的
        // mid(包括)前的是小的
        int mid = left - 1;

        // 以right为哨兵
        for (int i = left; i < right; ++i) {
            if (array[i] < array[right]) {
                // 那么找一个大于哨兵的值和第i个交换
                ++mid;
                if (i != mid) {
                    swap(array, i, mid);
                }
            }
        }

        ++mid;
        if (mid != right) {
            // 说明 此时的mid大于right
            swap(array, mid, right);
        }

        return mid;
    }

    private static int shuffleIndex(int[] array, int start, int end) {
        int tmp = array[start];
        while (start < end) {
            while (start < end && array[end] >= tmp) {
                end--;
            }
            array[start] = array[end];

            while (start < end && array[start] <= tmp) {
                start++;
            }
            array[end] = array[start];
        }
        array[start] = tmp;
        return start;
    }

    private static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = partition(array, start, end);
        quickSort(array, start, mid - 1);
        quickSort(array, mid + 1, end);
    }

    public static void quickSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    public static void selectorSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            int min = i;
            for (int j = i + 1; j < array.length; ++j) {
                if (array[min] > array[j]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(array, min, i);
            }
        }
    }

    private static void adjustHeap(int[] array, int root, int length) {
        int min = root;
        do {
            root = min;
            int left = root * 2 + 1;
            int right = left + 1;
            if (left < length && array[left] < array[min]) {
                min = left;
            }
            if (right < length && array[right] < array[min]) {
                min = right;
            }
            if (min != root) {
                swap(array, min, root);
            }
        } while (min != root);
    }

    public static void heapSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }

        for (int i = array.length / 2 - 1; i >= 0; --i) {
            adjustHeap(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; --i) {
            swap(array, i, 0);
            adjustHeap(array, 0, i);
        }
    }

    /**
     * 将两组有序的数进行排序
     */
    public static void mergeSort(int[] array, int start, int end) {
        if (null == array || start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid + 1, end);

        // 到此 start-mid有序 mid+1 - end有序
        // 将此连续的两段合并
        int preStart = start;
        int preEnd = mid;
        int lastStart = mid + 1;
        int lastEnd = end;
        int[] newArray = new int[end - start + 1];

        int oldStart = preStart;
        int newArrayIndex = 0;
        while (preStart <= preEnd && lastStart <= lastEnd) {
            newArray[newArrayIndex++] = array[preStart] < array[lastStart] ? array[preStart++] : array[lastStart++];
        }
        while (preStart <= preEnd) {
            newArray[newArrayIndex++] = array[preStart++];
        }
        while (lastStart <= lastEnd) {
            newArray[newArrayIndex++] = array[lastStart++];
        }

        // 重新赋值
        for (int i = 0; i < newArrayIndex; ++i) {
            array[oldStart++] = newArray[i];
        }
    }

    public static void countSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }
        int max = array[0];
        for (int e : array) {
            if (max < e) {
                max = e;
            }
        }
        // count[i] 表示 i出现的次数
        int[] count = new int[max + 1];
        for (int e : array) {
            count[e]++;
        }
        int index = 0;
        for (int i = 0; i <= max; ++i) {
            int cnt = count[i];
            while (cnt-- > 0) {
                array[index++] = i;
            }
        }
    }

    public static void main(String[] args) {
        int size = 30;
        Random random = new Random();
        int[] array = new int[size];

        for (int i = 0; i < size; ++i) {
            array[i] = Math.abs(random.nextInt() % 100);
        }
        display(array);
        int partition = partition(array, 0, array.length - 1);
        display(array);
        display(array, 0, partition);
        display(array, partition + 1, array.length - 1);

        quickSort(array);
        display(array);
    }
}
