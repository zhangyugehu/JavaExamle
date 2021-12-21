package com.t.order;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hutianhang on 2021/12/21
 */
public class DiffUtil<T extends Model> {
    private static final int DIFF_MAX = 20;

    public List<T> merge(List<T> originList, List<T> newList) {
        Map<String, T> freshMap = new LinkedHashMap<>(newList.size());
        Map<String, T> dirtyMap = new LinkedHashMap<>();
        log("newList > freshMap");
        for (int i = 0; i < newList.size(); i++) {
            T item = newList.get(i);
            if (item == null) continue;
            item.index = i;
            freshMap.put(item.key, item);
        }
        for (int i = 0; i < originList.size(); i++) {
            T origin = originList.get(i);
            if (origin == null) continue;
            if (!freshMap.containsKey(origin.key)) {
                // 新数据集合没有老数据的id, 记录脏数据
                if (i < DIFF_MAX) {
                    // 差异更新条数限制
//                    log("change origin " + origin.key +  " index: " + origin.index + " > " + i);
                    origin.index = i;
                    dirtyMap.put(origin.key, origin);
                    log("put " + origin.key + " to dirtyMap ");
                }
                continue;
            }
            log("set new model " + origin.key + " to " + i);
            // 新数据集合包含老id, 用新数据替换
            originList.set(i, freshMap.remove(origin.key));
        }
        if (!dirtyMap.isEmpty()) {
            // 删除脏数据
            Set<Map.Entry<String, T>> entries = dirtyMap.entrySet();
            for (Map.Entry<String, T> entry : entries) {
                T model = entry.getValue();
                log("remove old model "+ model.key + " in " + model.index);
                originList.remove(model);
            }
        }
        if (!freshMap.isEmpty()) {
            Set<Map.Entry<String, T>> entries = freshMap.entrySet();
            for (Map.Entry<String, T> entry : entries) {
                T model = entry.getValue();
                if (model.index < originList.size()) {
                    log("insert new model " + model.key + " to " + model.index);
                    originList.add(model.index, model);
                } else {
                    log("insert new model " + model.key + " to end");
                    originList.add(model);
                }
            }
        }
        return originList;
    }

    protected void log(String msg) {
        System.out.println("Diff: " + msg);
    }
}
