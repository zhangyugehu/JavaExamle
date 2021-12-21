package com.t.order;

/**
 * Created by hutianhang on 2021/12/21
 */
public class Order extends Model {

    public Order(String text) {
        this.key = text;
    }

    @Override
    public String toString() {
//        return "{" + index + " - " + key + "}";
        return key;
    }
}
