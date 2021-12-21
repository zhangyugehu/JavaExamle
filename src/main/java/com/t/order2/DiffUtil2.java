package com.t.order2;

import com.t.order.DiffUtil;

import java.util.*;

/**
 * Created by hutianhang on 2021/12/21
 */
public class DiffUtil2<T extends Model2> {

    /**
     * A    B
     * B    C
     * C    D
     * D
     * E
     * F
     * @param originList
     * @param newList
     * @param offset
     * @return
     */
    public List<T> merge(List<T> originList, List<T> newList, int offset) {
        List<T> result = new ArrayList<>(originList);
        Map<String, T> newMap = new HashMap<>();
        for (int i = 0; i < newList.size(); i++) {
            T newer = newList.get(i);
            newer.index = offset + i;
//            newMap.put(newer.key, newer);
        }

        for (int i = 0; i < originList.size(); i++) {
            T origin = originList.get(i);
            origin.index = i;
            for (int j = 0; j < newList.size(); j++) {
                T newer = newList.get(j);
                if (Objects.equals(origin.key, newer.key) || Objects.equals(origin.root, newer.root)) {
                    originList.add(newer.index, newer);
                    originList.remove(origin.index);
                } else {
                    newMap.put(newer.key, newer);
                }
            }
//            if (newMap.containsKey(origin.key)) {
//                log("重复数据: " + origin.key + " " + origin.index);
//                // 用新数据替换
//                T newer = newMap.remove(origin.key);
////                originList.set(newer.index, newer);
//                originList.add(newer.index, newer);
//                originList.remove(origin);
//            } else {
//                log("不存在的数据: " + origin.key + " " + origin.index);
//            }
        }

        if (newMap.isEmpty()) {
            log("没有新数据");
        } else {
            for (Map.Entry<String, T> entry: newMap.entrySet()) {
                T newData = entry.getValue();
                log("新数据: " + newData);
                result.add(newData);
            }
        }
        return result;
    }

    void log(String msg) {
        System.out.println("Diff: " + msg);
    }
}
