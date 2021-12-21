package com.t.order2;

import com.t.order.DiffUtil;

import java.util.*;
import java.util.function.Consumer;

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
     * @param freshList
     * @param offset
     * @return
     */
    public List<T> merge(List<T> originList, List<T> freshList, int offset) {
        List<T> result = new ArrayList<>(originList);
        Map<String, T> freshMap = new HashMap<>();
        for (int i = 0; i < freshList.size(); i++) {
            T newer = freshList.get(i);
            newer.index = offset + i;
            freshMap.put(newer.key, newer);
        }
        for (int i = 0; i < originList.size(); i++) {
            originList.get(i).index = i;
        }

        int step = 1;
        for (int i = 0; i < originList.size(); i++) {
            T origin = originList.get(i);
            origin.index = i;
            boolean hasAction = false;
            for (T fresh : freshList) {
                if (Objects.equals(origin.key, fresh.key)/* || Objects.equals(origin.root, fresh.root)*/) {
                    log("=========" + step++ + "=========");
                    if (origin.index == fresh.index) {
                        log("same index. replace " + fresh.key + " > " + origin.key);
                        result.set(origin.index, freshMap.remove(fresh.key));
                    } else {
                        log("remove " + result.get(origin.index) + " from " + origin.index);
                        result.remove(origin.index);

                        log("add " + fresh.key + " to " + fresh.index);
                        result.add(fresh.index, freshMap.remove(fresh.key));
                    }
                    hasAction = true;
                }
            }

            if (hasAction) {
                log(ListUtils.toString(result));
            }
        }

        if (freshMap.isEmpty()) {
            log("freshMap isEmpty");
        } else {
            List<Map.Entry<String, T>> l = new ArrayList<>(freshMap.entrySet());
            l.sort(Comparator.comparingInt(t0 -> t0.getValue().index));
            for (Map.Entry<String, T> entry : l) {
                T newData = entry.getValue();
                if (newData.index < result.size()) {
                    log("insert newData: " + newData + " to " + newData.index);
                    result.add(newData.index, newData);
                } else {
                    result.add(newData);
                    log("insert newData: " + newData + " to end " + "(" + newData.index);
                }
            }
        }
        return result;
    }

    void log(String msg) {
        System.out.println("Diff: " + msg);
    }
}
