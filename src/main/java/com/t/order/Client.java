package com.t.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hutianhang on 2021/12/21
 */
public class Client {

    public static void main(String[] args) {
//        Case<Order> orderCase = loadMoreCase();
        Case<Order> orderCase = refreshCase();

        System.out.println("===========start===========");
        print(orderCase.originList, orderCase.newList);

        System.out.println("===========merge===========");
        List<Order> result = new DiffUtil<Order>().merge(orderCase.originList, orderCase.newList);

        System.out.println("===========result===========");
        print(result);
    }












    static class Case<T extends  Model> {
        List<T> originList;
        List<T> newList;

        public Case(List<T> originList, List<T> newList) {
            this.originList = originList;
            this.newList = newList;
        }
    }

    private static Case<Order> loadMoreCase() {
        Case<Order> result = new Case<>(new ArrayList<>(), new ArrayList<>());
        Collections.addAll(result.originList,
                new Order("A"),
                new Order("B"),
                new Order("C"),
                new Order("D"),
                new Order("E")
        );
        Collections.addAll(result.newList,
//                new Order("A"),
//                new Order("B"),
//                new Order("C"),
//                new Order("D"),
//                new Order("E"),
                new Order("F"),
                new Order("G"),
                new Order("H"),
                new Order("I"),
                new Order("J")
        );
        return result;
    }

    private static Case<Order> refreshCase() {
        Case<Order> result = new Case<>(new ArrayList<>(), new ArrayList<>());
        Collections.addAll(result.newList,
                new Order("A"),
                new Order("B"),
                new Order("C"),
                new Order("D"),
                new Order("E")
        );
        Collections.addAll(result.originList,
//                new Order("A"),
                new Order("B"),
                new Order("C"),
                new Order("D"),
                new Order("F"),
                new Order("E")
//                new Order("G"),
//                new Order("H"),
//                new Order("I"),
//                new Order("J")
        );
        return result;
    }

    private static <T> T safeGet(List<T> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    private static void print(List<Order> originList, List<Order> newList) {
        int length = Math.max(originList.size(), newList.size());
        for (int i = 0; i < length; i++) {
            System.out.println(i + ": " + safeGet(originList, i) + " - " + safeGet(newList, i));
        }
    }

    private static void print(List<Order> list) {
        for (Order order : list) {
            System.out.println(order.index + " - " + order.key);
        }
    }
}
