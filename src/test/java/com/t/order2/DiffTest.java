package com.t.order2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * Created by hutianhang on 2021/12/22
 */
public class DiffTest {
    
    static class WrapString {
        public final String str;

        public WrapString(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }
    
    DiffUtil<WrapString> diffUtil = new DiffUtil<>((o, n) -> Objects.equals(o.str, n.str));

    /**
     * A    A
     * B    B
     * C    C
     * D
     * >>> ABCD
     */
    @Test
    public void case0() {
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D")),
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D")),
                        List.of(new WrapString("B"), new WrapString("C"), new WrapString("D")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D")),
                        List.of(new WrapString("C"), new WrapString("B"), new WrapString("D")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D"), new WrapString("E"), new WrapString("F")),
                        List.of(new WrapString("D"), new WrapString("E"), new WrapString("F")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D"), new WrapString("E"), new WrapString("F")),
                        List.of(new WrapString("E"), new WrapString("F"), new WrapString("G")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D"), new WrapString("E"), new WrapString("F")),
                        List.of(new WrapString("E"), new WrapString("G"), new WrapString("F")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D"), new WrapString("E"), new WrapString("F")),
                        List.of(new WrapString("F"), new WrapString("G")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D")),
                        List.of(new WrapString("D"), new WrapString("E"), new WrapString("F")),
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
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D")),
                        List.of(new WrapString("D"), new WrapString("E"), new WrapString("F")),
                        2
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABDEFC", result);
    }

    /**
     * A
     * B
     * C
     * C    D
     * D    E
     *      F
     * >>> ABCDEFC
     */
    @Test
    public void case9() {
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("C"), new WrapString("D")),
                        List.of(new WrapString("D"), new WrapString("E"), new WrapString("F")),
                        3
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("ABCDEFC", result);
    }

    /**
     *     A    K
     *     B    A
     *     C    E
     *     D    C
     *     E    B
     *     F    D
     *     G    F
     *     H    G
     *     I    H
     *     J    I
     * >>> KAECBDFGHIJ
     */
    @Test
    public void case10() {
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D"), new WrapString("E"), new WrapString("F"), new WrapString("G"), new WrapString("H"), new WrapString("I"), new WrapString("J")),
                        List.of(new WrapString("K"), new WrapString("A"), new WrapString("E"), new WrapString("C"), new WrapString("B"), new WrapString("D"), new WrapString("F"), new WrapString("G"), new WrapString("H"), new WrapString("I")),
                        0
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("KAECBDFGHIJ", result);
    }

    @Test
    public void case101() {
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(
                                new WrapString("A"),
                                new WrapString("B"),
                                new WrapString("C"),
                                new WrapString("D"),
                                new WrapString("E"),
                                new WrapString("F"),
                                new WrapString("G"),
                                new WrapString("H"),
                                new WrapString("I"),
                                new WrapString("J")
                        ),
                        List.of(
                                new WrapString("J"),
                                new WrapString("I"),
                                new WrapString("H"),
                                new WrapString("G"),
                                new WrapString("F"),
                                new WrapString("E"),
                                new WrapString("D"),
                                new WrapString("C"),
                                new WrapString("B"),
                                new WrapString("A")
                        ),
                        0
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("JIHGFEDCBA", result);
    }



    /**
     *     A    K
     *     B    A
     *     C    B
     *     D    C
     * >>> KABCD
     */
    @Test
    public void case11() {
        String result = ListUtils.toString(diffUtil
                .merge(
                        List.of(new WrapString("A"), new WrapString("B"), new WrapString("C"), new WrapString("D")),
                        List.of(new WrapString("K"), new WrapString("A"), new WrapString("B"), new WrapString("C")),
                        0
                ));
        System.out.println("result>>> " + result);
        Assertions.assertEquals("KABCD", result);
    }
}
