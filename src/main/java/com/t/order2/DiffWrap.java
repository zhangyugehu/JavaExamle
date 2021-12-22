package com.t.order2;

/**
 * Created by hutianhang on 2021/12/21
 */
public class DiffWrap<T> {
    public DiffWrap(T source, int index) {
        this.source = source;
        this.index = index;
    }

    public T source;
    public int index = -1;
}
