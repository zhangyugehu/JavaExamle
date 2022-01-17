package com.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by hutianhang on 2022/1/16
 */
public class Array {

    static final int[][] ARR = new int[][] {
            new int[]{1,3,5,7,9},
            new int[]{2,4,6,8,10},
            new int[]{11,13,15,17,19},
            new int[]{12,14,16,18,20},
    };

    @Test
    void Client() {
//        System.out.println(find(4, ARR));
        String target = "We Are Happy.";
        char[] result = replaceBlank(target.toCharArray());
        System.out.println(result);
        Assertions.assertArrayEquals(target.replace(" ", "%20").toCharArray(), result);
    }


    //region 二维数组查找
    /**
     * 在一个二位数组中(每个一维数组的长度相同)
     * 每一行都按照从左到右递增的顺序排序
     * 每一列都按照从上到下递增的顺序排序
     * 请完成一个函数 输入这样的一个二维数组和一个整数， 判断数组中是否含有该整数
     * @param target
     * @param arr
     */
    boolean find(int target, int[][] arr) {
//        return findSolution_O_mn(target, arr);
        return findSolution_O_n(target, arr);
    }

    /**
     * 从上到下，从右到左
     * @param target
     * @param arr
     * @return
     */
    private boolean findSolution_O_n(int target, int[][] arr) {
        int iCount = arr[0].length;
        int jCount = arr.length;
        int iIndex = iCount - 1;
        int jIndex = 0;
        while (jIndex < jCount && iIndex >= 0) {
            System.out.println("i: "+ iIndex + ", j: " + jIndex);
            int value = arr[jIndex][iIndex];
            if (value == target) return true;
            else if (value > target) iIndex --;
            else jIndex ++;
        }
        return false;
    }

    private boolean findSolution_O_mn(int target, int[][] arr) {
        for (int[] ints : arr) {
            for (int it : ints) {
                if (it == target) return true;
            }
        }
        return false;
    }
    //endregion

    //region 替换空格
    private char[] replaceBlank(char[] charArr) {
        char[] ret = new char[charArr.length];
        int offset = 0;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == ' ') {
                ret = reSize(ret, charArr.length + offset + 2);
                ret[i + offset ++] = '%';
                ret[i + offset ++] = '2';
                ret[i + offset] = '0';
            } else {
                ret[i + offset] = charArr[i];
            }
        }
        return ret;
    }
    //endregion

    private char[] reSize(char[] target, int size) {
        if (target.length <= size) {
            char[] tmp = new char[target.length + 2];
            System.arraycopy(target, 0, tmp, 0, target.length);
            return tmp;
        }
        return target;
    }
}
