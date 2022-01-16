package com.t.lru;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.LruCache;

import java.util.Random;

/**
 * Created by hutianhang on 2021/12/30
 */
public class LruCacheTest {
    @Test
    void sizedTest() {
        LruCache<String, String> cache = new LruCache<>(10);

        Random random = new Random();
        for (int i = 1; i < 100; i++) {
            int rInt = random.nextInt(20);
            cache.put("k" + rInt, "v" + rInt);
            printMap(cache);
        }

    }

    private void printMap(LruCache<String, String> cache) {

        StringBuilder sb = new StringBuilder();
        for (String st : cache.values()) {

            sb.append(st).append(", ");
        }
        sb.replace(sb.length() - 2 , sb.length(), "\n");
        System.out.println(sb);
    }
}
