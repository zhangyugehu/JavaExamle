package com.t.order2;

import com.t.order.Model;

/**
 * Created by hutianhang on 2021/12/21
 */
public class Order2 extends Model2 {

    public Order2(String text) {
        this.key = text;
    }

    @Override
    public String toString() {
//        return "{" + index + " - " + key + "}";
        return key;
    }
}
