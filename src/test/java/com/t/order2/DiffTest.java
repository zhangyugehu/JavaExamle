package com.t.order2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * Created by hutianhang on 2021/12/22
 */
public class DiffTest {

    /**
     * A    A
     * B    B
     * C    C
     * D
     * >>> ABCD
     */
    @Test
    public void case0() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D"),
                        List.of("A", "B", "C"),
                        0
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABCD", result);
    }

    /**
     * A    B
     * B    C
     * C    D
     * D
     * >>> BCDA
     */
    @Test
    public void case1() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D"),
                        List.of("B", "C", "D"),
                        0
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("BCDA", result);
    }

    /**
     * A    C
     * B    B
     * C    D
     * D
     * >>> CBDA
     */
    @Test
    public void case2() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D"),
                        List.of("C", "B", "D"),
                        0
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("CBDA", result);
    }

    /**
     * A
     * B
     * C
     * D    D
     * E    E
     * F    F
     * >>> ABCDEF
     */
    @Test
    public void case3() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D", "E", "F"),
                        List.of("D", "E", "F"),
                        3
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABCDEF", result);
    }

    /**
     * A
     * B
     * C
     * D    E
     * E    F
     * F    G
     * >>> ABCEFGD
     */
    @Test
    public void case4() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D", "E", "F"),
                        List.of("E", "F", "G"),
                        3
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABCEFGD", result);
    }

    /**
     * A
     * B
     * C
     * D    E
     * E    G
     * F    F
     * >>> ABCEGFD
     * ABCEGDF
     */
    @Test
    public void case5() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D", "E", "F"),
                        List.of("E", "G", "F"),
                        3
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABCEGDF", result);
    }

    /**
     * A
     * B
     * C
     * D    F
     * E    G
     * F
     * >>> ABCFGDE
     */
    @Test
    public void case6() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D", "E", "F"),
                        List.of("F", "G"),
                        3
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABCFGDE", result);
    }

    /**
     * A
     * B
     * C
     * D    D
     *      E
     *      F
     * >>> ABCDEF
     */
    @Test
    public void case7() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D"),
                        List.of("D", "E", "F"),
                        3
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABCDEF", result);
    }

    /**
     * A
     * B
     * C    D
     * D    E
     *      F
     * >>> ABDEFC
     */
    @Test
    public void case8() {
        String result = ListUtils.toString(new DiffUtil<>(Objects::equals)
                .merge(
                        List.of("A", "B", "C", "D"),
                        List.of("D", "E", "F"),
                        2
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABDEFC", result);
    }
}
