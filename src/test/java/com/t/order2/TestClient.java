package com.t.order2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hutianhang on 2021/12/21
 */
public class TestClient {

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

        List<Order2> result = new DiffUtil2<Order2>().merge(
                originList,
                newList,
                0
        );
        String except = "BCDAEF";
        String resultStr = ListUtils.toString(result, o -> o.key);

        Assertions.assertEquals(except, resultStr);
    }
}
