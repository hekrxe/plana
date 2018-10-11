package com.hekrxe.plana.minor.algorithm.sword;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

/**
 * User: tanhuayou
 * Date: 2018/9/3
 */
public class FindAndSort {
    public static final Random SHARD_RANDOM = new Random();


    public static int indexOfBinarySearch(int[] sortedArray, int expected) {
        if (null == sortedArray || sortedArray.length <= 0) {
            return -1;
        }

        int left = 0;
        int right = sortedArray.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            int element = sortedArray[mid];
            if (element == expected) {
                return mid;
            } else if (element < expected) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }


    public static int indexOfLast(int[] array, int expected) {
        if (null == array || array.length <= 0) {
            return -1;
        }

        int left = 0;
        int right = array.length;
        int mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            int ele = array[mid];

            // xxx
            if (ele <= expected) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }
        // xxx
        if (array[right] == expected) {
            return right;
        }

        return -1;
    }

    public static int indexOfFirst(int[] array, int expected) {
        if (null == array || array.length <= 0) {
            return -1;
        }

        int left = 0;
        int right = array.length;
        int mid;
        while (left <= right) {
            mid = (left + right) >>> 1;
            int ele = array[mid];

            if (ele >= expected) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
        if (array[left] == expected) {
            return left;
        }

        return -1;
    }


    public static int partition(int[] array, int left, int right) {
        if (null == array || array.length <= 1 || right >= array.length) {
            return -1;
        }

        int index = randomInRange(left, right);
        swap(array, index, right);

        int small = left - 1;
        for (index = left; index < right; index++) {
            if (array[index] < array[right]) {
                ++small;
                if (small != index) {
                    swap(array, small, index);
                }
            }
        }

        small++;
        if (small != right) {
            swap(array, small, right);
        }

        return small;
    }


    public static int shuffleIndex(int[] array, int left, int right, Function<Integer, Boolean> action) {

        while (left < right) {
            while (left < right && action.apply(array[left])) {
                left++;
            }
            while (left < right && !action.apply(array[right])) {
                right--;
            }
            if (left < right) {
                swap(array, left, right);
            }
        }

        return left > right ? right : left;
    }


    public static void quickSort(int[] array) {
        if (null == array || array.length <= 1) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left >= right) return;

        int partition = partition(array, left, right);
        quickSort(array, partition + 1, right);
        if (partition > 1) {
            quickSort(array, left, partition - 1);
        }
    }

    /**
     * @param start include
     * @param end   include
     */
    public static int randomInRange(int start, int end) {
        int min = Math.min(start, end);
        int max = Math.max(start, end);
        return min + SHARD_RANDOM.nextInt(max - min + 1);
    }

    public static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void display(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) throws InterruptedException {
        int[] array = {1, 2, 3, 4, 4, 3, 2, 1, 1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1, 2, 3, 4, 3, 2, 1};
        int shuffleIndex = shuffleIndex(array, 0, array.length - 1, (e) -> (e & 1) == 0);
        System.out.println(shuffleIndex);

        display(array);

        quickSort(array);

        display(array);


    }
}
