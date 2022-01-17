package com.code;

import org.junit.jupiter.api.Test;

/**
 * 变态跳楼梯
 * n 阶楼梯每次可以跳 1阶 2阶或n阶  计算多少种跳法
 * f(n) = f(n-1) + f(n-2) + ... + f(1)
 * f(n - 1) = f(n - 2) + f(n - 3) + ... f(1)
 * => f(n) = f(n - 1) * 2
 * Created by hutianhang on 2022/1/16
 */
public class StepX {

    @Test
    void solution() {
        System.out.println(fn(10));
    }

    int fn(int n) {
        int ret = 1;
        if (n == 0) return  0;
        else if (n == 1) return  1;
        else for (int a = 1, i = 1; i < n; i++) {
            ret = 2 * a;
            a = ret;
        }
        return ret;
    }
}
