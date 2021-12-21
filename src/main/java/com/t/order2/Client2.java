package com.t.order2;

import com.t.order.DiffUtil;
import com.t.order.Model;
import com.t.order.Order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hutianhang on 2021/12/21
 */
public class Client2 {

    public static void main(String[] args) {
//        Case<Order2> orderCase = loadMoreCase();
        Case<Order2> orderCase = refreshCase();

        System.out.println("===========start===========");
        print(orderCase.originList, orderCase.newList);

        System.out.println("===========merge===========");
        List<Order2> result = new DiffUtil2<Order2>().merge(
                orderCase.originList,
                orderCase.newList,
                0
        );

        System.out.println("===========result===========");
        print(result);
    }












    public static class Case<T extends Model2> {
        List<T> originList;
        List<T> newList;

        public Case(List<T> originList, List<T> newList) {
            this.originList = originList;
            this.newList = newList;
        }
    }

    private static Case<Order2> loadMoreCase() {
        Case<Order2> result = new Case<>(new ArrayList<>(), new ArrayList<>());
        Collections.addAll(result.originList,
                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E")
        );
        Collections.addAll(result.newList,
//                new Order2("A"),
//                new Order2("B"),
//                new Order2("C"),
//                new Order2("D"),
//                new Order2("E"),
                new Order2("F"),
                new Order2("G"),
                new Order2("H"),
                new Order2("I"),
                new Order2("J")
        );
        return result;
    }

    private static Case<Order2> refreshCase() {
        Case<Order2> result = new Case<>(new ArrayList<>(), new ArrayList<>());
        Collections.addAll(result.originList,
                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E")
        );
        Collections.addAll(result.newList,
//                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E"),
                new Order2("F")
//                new Order2("G"),
//                new Order2("H"),
//                new Order2("I"),
//                new Order2("J")
        );
        return result;
    }

    private static Case<Order2> refreshPartCase() {
        Case<Order2> result = new Case<>(new ArrayList<>(), new ArrayList<>());
        Collections.addAll(result.originList,
                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D"),
                new Order2("E"),
                new Order2("F")
        );
        Collections.addAll(result.newList,
//                new Order2("A"),
                new Order2("B"),
                new Order2("C"),
                new Order2("D")
//                new Order2("E"),
//                new Order2("F")
//                new Order2("G"),
//                new Order2("H"),
//                new Order2("I"),
//                new Order2("J")
        );
        return result;
    }

    private static <T> T safeGet(List<T> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    private static void print(List<Order2> originList, List<Order2> newList) {
        int length = Math.max(originList.size(), newList.size());
        for (int i = 0; i < length; i++) {
            System.out.println(i + ": " + safeGet(originList, i) + " - " + safeGet(newList, i));
        }
    }

    private static void print(List<Order2> list) {
        for (Order2 order : list) {
            System.out.println(order.index + " - " + order.key);
        }
    }
}
