package com.t.order2;

import java.util.List;

/**
 * Created by hutianhang on 2021/12/21
 */
public class ListUtils {

    public interface IConvert<I, O> {
        O invoke(I input);
    }

    public static <T> String toString(List<T> list) {
        return toString(list, Object::toString);
    }

    public static <T> String toString(List<T> list, IConvert<T, String> convert) {
        StringBuilder sb = new StringBuilder();
        for (T data : list) {
            sb.append(convert.invoke(data));
        }
        return sb.toString();
    }
}
