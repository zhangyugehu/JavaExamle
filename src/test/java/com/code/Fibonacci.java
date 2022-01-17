package com.code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * f(n) = f(n-1) + f(n-2)
 */
public class Fibonacci {
    private static final int COUNT = 10000;

    @Test
    void Solution1() {
        Assertions.assertEquals(63245986, fn2(39));
    }

    @Test
    void Solution2() {
//        for (int i = 0; i < COUNT; i++) {
//            Assertions.assertEquals(63245986, fn2(39));
//        }
        System.out.println(fn3(10));
    }

    @Test
    void Solution3() {
        for (int i = 0; i < COUNT; i++) {
            Assertions.assertEquals(63245986, fn3(39));
        }
    }

    int fn1(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fn1(n - 1) + fn1(n - 2);
    }

    long fn2(int n) {
        int index = 0;
        Map<Integer, Long> cache = new HashMap<>(n);
        cache.put(0, 0L);
        cache.put(1, 1L);
        while(index <= n) {
            if (index > 1 && cache.get(index) == null) {
                cache.put(index, cache.get(index - 1) + cache.get(index - 2));
            }
//            System.out.println("index: " + index + ">>> " + cache.get(index));
            index ++;
        }
        return cache.get(n);
    }

    long fn3(int n) {
        if (n == 0) return 0;
        else if (n == 1) return 1;
        else {
            long latest = 1;
            long maxLeaf = 0;
            long result = 0;
            for (int i = 2; i <= n; i++) {
                result = latest + maxLeaf;
                maxLeaf = latest;
                latest = result;
            }
            return result;
        }
    }
}
