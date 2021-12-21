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
        // 记录新数据index
        for (int i = 0; i < freshList.size(); i++) freshList.get(i).index = offset + i;

        int step = 1;
        for (int i = 0; i < originList.size(); i++) {
            T origin = originList.get(i);
            // 记录老数据index
            origin.index = i;
            boolean hasAction = false;
            Iterator<T> iterator = freshList.iterator();
            while (iterator.hasNext()) {
                // 遍历新数据, 存在和老数据相同的item替换掉老的
                T fresh = iterator.next();
                if (Objects.equals(origin.key, fresh.key)/* || Objects.equals(origin.root, fresh.root)*/) {
                    log("=========" + step++ + "=========");
                    if (origin.index == fresh.index) {
                        // 如果index相同 直接替换
                        log("same index. replace " + fresh.key + " > " + origin.key);
                        result.set(origin.index, fresh);
                        iterator.remove();
                    } else {
                        // 如果index不相同 将老的删掉再按新的index加入新数据
                        log("remove " + result.get(origin.index) + " from " + origin.index);
                        result.remove(origin.index);

                        log("add " + fresh.key + " to " + fresh.index);
                        result.add(fresh.index, fresh);
                        iterator.remove();
                    }
                    hasAction = true;
                }
            }

            if (hasAction) {
                log(ListUtils.toString(result));
            }
        }

        if (!freshList.isEmpty()) {
            // 未匹配的新数据按index插入
            for (T newData : freshList) {
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
