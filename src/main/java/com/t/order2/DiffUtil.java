package com.t.order2;

import java.util.*;

/**
 * Created by hutianhang on 2021/12/21
 */
public class DiffUtil<T> {

    public interface OnDiff<T> {
        boolean isSame(T origin, T fresh);
    }

    private final OnDiff<T> onDiff;

    public DiffUtil(OnDiff<T> differ) {
        this.onDiff = differ;
    }

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
        List<DiffWrap<T>> originListWrap = new ArrayList<>(originList.size());
        List<DiffWrap<T>> freshListWrap = new ArrayList<>(freshList.size());
        // 记录老数据index
        for (int i = 0; i <originList.size(); i++) {
            originListWrap.add(new DiffWrap<>(originList.get(i), i));
        }
        // 记录新数据index
        for (int i = 0; i < freshList.size(); i++) {
            freshListWrap.add(new DiffWrap<>(freshList.get(i), offset + i));
        }

        int step = 1;
        for (int i = 0; i < originList.size(); i++) {
            DiffWrap<T> origin = originListWrap.get(i);
            boolean hasSame = false;
            Iterator<DiffWrap<T>> iterator = freshListWrap.iterator();
            while (iterator.hasNext()) {
                // 遍历新数据, 存在和老数据相同的item替换掉老的
                DiffWrap<T> fresh = iterator.next();
                log("compare " + origin.source + " <> " + fresh.source);
                if (onDiff.isSame(origin.source, fresh.source)) {
                    log("=========" + step++ + "=========");
                    if (origin.index == fresh.index) {
                        // 如果index相同 直接替换
                        log("same index. replace " + fresh.source + " > " + origin.source);
                        originListWrap.set(origin.index, fresh);
                        iterator.remove();
                    } else {
                        // 如果index不相同 将老的删掉再按新的index加入新数据
                        log("remove " + originListWrap.get(origin.index).source + " from " + origin.index);
                        originListWrap.remove(origin.index);

                        log("add " + fresh.source + " to " + fresh.index);
                        originListWrap.add(fresh.index, fresh);
                        iterator.remove();
                    }
                    hasSame = true;
                }
            }

            if (hasSame) {
                log("current list: " + ListUtils.toString(originListWrap, o -> o.source.toString()));
            }
        }

        if (!freshListWrap.isEmpty()) {
            // 未匹配的新数据按index插入
            for (DiffWrap<T> newData : freshListWrap) {
                if (newData.index < originListWrap.size()) {
                    log("insert newData: " + newData.source + " to " + newData.index);
                    originListWrap.add(newData.index, newData);
                } else {
                    originListWrap.add(newData);
                    log("insert newData: " + newData.source + " to end " + "(" + newData.index);
                }
            }
        }
        List<T> result = new ArrayList<>(originListWrap.size());
        for (DiffWrap<T> comparable : originListWrap) {
            result.add(comparable.source);
        }
        return result;
    }

    void log(String msg) {
        System.out.println("Diff: " + msg);
    }
}
