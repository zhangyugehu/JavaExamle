package com.t.order2;

/**
 * Created by hutianhang on 2021/12/21
 */
public class Order2 {

    public String key;

    public Order2(String text) {
        this.key = text;
    }

    @Override
    public String toString() {
//        return "{" + index + " - " + key + "}";
        return key;
    }
}
