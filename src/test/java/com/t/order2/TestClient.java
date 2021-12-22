package com.t.order2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * Created by hutianhang on 2021/12/21
 */
public class TestClient {

    final DiffUtil.OnDiff<Order2> consumer = (origin, fresh) -> Objects.equals(origin.key, fresh.key);

    @Test
    public void refreshPartCase() {
        List<Order2> originList = new ArrayList<>();
        List<Order2> newList = new ArrayList<>();
        Collections.addAll(originList,
                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E"),
                new Order2("F")
        );
        Collections.addAll(newList,
                new Order2("B"),
                new Order2("C"),
                new Order2("D")
        );

        List<Order2> result = new DiffUtil<Order2>(consumer).merge(
                originList,
                newList,
                0
        );
        String except = "BCDAEF";
        String resultStr = ListUtils.toString(result);

        Assertions.assertEquals(except, resultStr);
    }

    @Test
    public void refreshPartCase2() {
        List<Order2> originList = new ArrayList<>();
        List<Order2> newList = new ArrayList<>();
        Collections.addAll(originList,
                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E"),
                new Order2("F")
        );
        Collections.addAll(newList,
                new Order2("E"),
                new Order2("F"),
                new Order2("G")
        );

        List<Order2> result = new DiffUtil<Order2>(consumer).merge(
                originList,
                newList,
                3
        );
        String except = "ABCEFDG";
        String resultStr = ListUtils.toString(result);

        Assertions.assertEquals(except, resultStr);
    }

    @Test
    public void refreshCase() {
        List<Order2> originList = new ArrayList<>();
        List<Order2> newList1 = new ArrayList<>();
        List<Order2> newList2 = new ArrayList<>();
        Collections.addAll(originList,
                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E"),
                new Order2("F")
        );
        Collections.addAll(newList1,
                new Order2("B"),
                new Order2("C"),
                new Order2("D")
        );
        Collections.addAll(newList2,
                new Order2("E"),
                new Order2("F"),
                new Order2("G")
        );

        List<Order2> result = new DiffUtil<Order2>(consumer).merge(
                originList,
                newList1,
                0
        );

        result = new DiffUtil<Order2>(consumer).merge(
                result,
                newList2,
                3
        );
        String except = "BCDEFGA";
        String resultStr = ListUtils.toString(result);

        Assertions.assertEquals(except, resultStr);
    }

    Order2 createOrder(int i, int j) {
        return new Order2("[" + (i + j) + "]");
    }

    @Test
    public void refreshCaseAuto() {

        int PAGE_SIZE = 3;
        DiffUtil<Order2> diff = new DiffUtil<>(consumer);
        List<Order2> result = new ArrayList<>();
        for (int i = 0; i < 2 * PAGE_SIZE; i += PAGE_SIZE) {
            for (int j = 0; j < PAGE_SIZE; j++) {
                result.add(createOrder(i, j));
            }
        }
        for (int i = 0; i < 5 * PAGE_SIZE; i += PAGE_SIZE) {
            List<Order2> addList = new ArrayList<>();
            for (int j = 1; j < PAGE_SIZE + 1; j++) {
                addList.add(createOrder(i, j));
            }
            System.out.println("origin>>> " + ListUtils.toString(result));
            System.out.println("addList>>> " + ListUtils.toString(addList));
            List<Order2> merged = diff.merge(result, addList, i);
            System.out.println("merged>>> " + ListUtils.toString(merged));
            result = merged;
        }
    }

    @Test
    public void refreshCaseFull() {
        int PAGE_SIZE = 3;
        List<Order2> listOrigin = ListUtils.newArrayList(
                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E"),
                new Order2("F")
        );
        List<Order2> listAdd1 = ListUtils.newArrayList(
                new Order2("B"),
                new Order2("C"),
                new Order2("D")
        );
        List<Order2> listAdd2 = ListUtils.newArrayList(
                new Order2("E"),
                new Order2("F"),
                new Order2("G")
        );
        List<Order2> listAdd3 = ListUtils.newArrayList(
                new Order2("H"),
                new Order2("I"),
                new Order2("J")
        );
        DiffUtil<Order2> diff = new DiffUtil<>(consumer);
        Assertions.assertEquals("", ListUtils.toString(
                diff.merge(
                        diff.merge(
                                diff.merge(
                                        listOrigin,
                                        listAdd1,
                                        PAGE_SIZE * 0
                                ),
                                listAdd2,
                                PAGE_SIZE * 1
                        ),
                        listAdd3,
                        PAGE_SIZE * 2
                )
        ));
    }


    /**
     * A
     * B
     * C    D
     * D    C
     *      E
     */
    @Test
    public void simpleCase() {
        List<String> merged = new DiffUtil<String>(Objects::equals).merge(
                List.of("A", "B", "C", "D"),
                List.of("D", "C", "E"),
//                List.of("C", "D", "E", "F"),
                2
        );
        System.out.println("merged List: " + ListUtils.toString(merged));
    }
}
