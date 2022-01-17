package com.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 跳楼梯
 * n 阶楼梯每次可以跳 1阶 2阶,    计算多少种跳法
 * f(n) = f(n-1) + f(n-2)
 * Created by hutianhang on 2022/1/16
 */
public class Step {

    private static final int TEST_COUNT = 100;

    private static final List<Integer> mDic = new ArrayList<>();

    @BeforeAll
    static void prepare() {
        mDic.add(0);
        mDic.add(1);
        mDic.add(2);
        mDic.add(3);
        mDic.add(5);
        mDic.add(8);
        mDic.add(13);
        mDic.add(21);
        mDic.add(34);
        mDic.add(55);
    }

    @Test
    void Solution1() {
//        for (int i = 0; i < TEST_COUNT; i++) {
//            for (int j = 0; j < mDic.size(); j++) {
//                Assertions.assertEquals(mDic.get(j), fn1(j));
//            }
//        }
        System.out.println(fn2(10));
    }

    @Test
    void Solution2() {
        for (int i = 0; i < TEST_COUNT; i++) {
            for (int j = 0; j < mDic.size(); j++) {
                Assertions.assertEquals(mDic.get(j), fn2(j));
            }
        }
    }

    int fn1(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        return fn1(n - 1) + fn1(n - 2);
    }

    int fn2(int n) {
        int fn0 = 0;
        int fnp1 = 1;
        int fnp2 = 2;
        int ret = 0;
        if (n == 0) return fn0;
        else if (n == 1) return fnp1;
        else if (n == 2) return fnp2;
        else for (int i = 3; i <= n; i++) {
            ret = fnp1 + fnp2;
            fnp1 = fnp2;
            fnp2 = ret;
        }
        return ret;
    }

}
