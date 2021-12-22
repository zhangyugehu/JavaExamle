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
     *
     * @param originList
     * @param freshList
     * @param offset
     * @return
     */
    public List<T> merge(List<T> originList, List<T> freshList, int offset) {
        log(originList, freshList, offset);
        List<T> resultList = new ArrayList<>(originList);
        List<DiffWrap<T>> freshListWrap = new ArrayList<>(freshList.size());
        // 记录新数据index
        for (int i = 0; i < freshList.size(); i++) {
            freshListWrap.add(new DiffWrap<>(freshList.get(i), offset + i));
        }

        int step = 1;
        Iterator<DiffWrap<T>> iterator = freshListWrap.iterator();
        while (iterator.hasNext()) {
            // 遍历新数据, 存在和老数据相同的item替换掉老的
            DiffWrap<T> fresh = iterator.next();
            boolean hasSame = false;
            for (int index = 0; index <resultList.size(); index++) {
                T origin = resultList.get(index);
                log("compare " + fresh.source + " <> " + origin);
                if (onDiff.isSame(origin, fresh.source)) {
                    log("=========" + step++ + "=========");
                    if (index == fresh.index) {
                        // 如果index相同 直接替换
                        log("same index " + index +". replace " + fresh.source + " > " + origin);
                        resultList.set(index, fresh.source);
                        iterator.remove();
                    } else {
                        // 如果index不相同 将老的删掉再按新的index加入新数据
                        T removed = resultList.remove(index);
                        log("remove " + removed + " from " + index);

                        log("add " + fresh.source + " to " + fresh.index);
                        resultList.add(fresh.index, fresh.source);
                        iterator.remove();
                    }
                    hasSame = true;
                }
            }

            if (hasSame) {
                log("current list: " + ListUtils.toString(resultList));
            }
        }

        if (!freshListWrap.isEmpty()) {
            // 未匹配的新数据按index插入
            for (DiffWrap<T> newData : freshListWrap) {
                if (newData.index < resultList.size()) {
                    log("insert newData: " + newData.source + " to " + newData.index);
                    resultList.add(newData.index, newData.source);
                } else {
                    resultList.add(newData.source);
                    log("insert newData: " + newData.source + " to end " + "(" + newData.index);
                }
            }
        }
        return resultList;
    }

    private void log(List<T> originList, List<T> freshList, int offset) {
        int max = Math.max(originList.size(), freshList.size() + offset);
        System.out.println("=================");
        for (int i = 0; i < max; i++) {
            StringBuilder line = new StringBuilder("    ");
            if (i < originList.size()) {
                line.append(originList.get(i));
            } else {
                line.append(" ");
            }
            int fixedIdx = i - offset;
            line.append("    ");
            if (fixedIdx >=0 && fixedIdx < freshList.size()) {
                line.append(freshList.get(fixedIdx));
            }
            System.out.println(line);
        }
        System.out.println("=================");
    }

    void log(String msg) {
        System.out.println("Diff: " + msg);
    }
}
