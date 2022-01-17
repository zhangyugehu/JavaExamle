package com.code;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 *
 * Created by hutianhang on 2022/1/16
 */
public class BSearch {

    @Test
    void Client() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] arr2 = new int[]{10, 9, 8, 7, 6, 4, 5, 2, 1, 3};
        System.out.println("1 in " + bSearch(arr, 1));
        System.out.println("10 in " + bSearch(arr, 10));
        System.out.println("5 in " + bSearch(arr, 5));
        System.out.println(Arrays.toString(sort(arr)));
        System.out.println(Arrays.toString(sort(arr2)));
        sortArray(arr);
        System.out.println(Arrays.toString(arr));
        sortArray(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * 二分查找正序数组
     * @param arr
     * @param target
     * @return
     */
    int bSearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;
            if (target == arr[mid]) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 奇偶分离并保证原始顺序
     * @param arr
     * @return
     */
    int[] sort(int[] arr) {
        int[] ret = new int[arr.length];
        int index = 0;
        for (int e : arr) {
            if (e % 2 == 1) ret[index++] = e;
        }
        for (int e : arr) {
            if (e % 2 == 0) ret[index++] = e;
        }
        return ret;
    }

    void sortArray(int[] arr) {
        long loopCount = 0;
        int end = arr.length;
        for (int i = 0; i < end; i++) {
            if (arr[i] % 2 == 0) {
//                System.out.println(i + "-b: " + Arrays.toString(arr));
                int tmp = arr[i];
                for (int j = i; j < arr.length; j++) {
                    loopCount ++;
                    if (j == arr.length - 1) {
                        arr[j] = tmp;
                    } else {
                        arr[j] = arr[j + 1];
                    }
                }
//                System.out.println(i + "-a: " + Arrays.toString(arr));
                i --;
                end --;
            } else {
//                System.out.println(i + "-skip: " + Arrays.toString(arr));
            }
        }
        System.out.println("loopCount: " + loopCount);
    }

}
