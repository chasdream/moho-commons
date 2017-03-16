/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo.zhu
 * Version: 1.0
 * Date: 2017-02-09
 */
package com.moho.commons.sort;

import com.google.common.collect.Ordering;

import java.util.List;

/**
 * 集合排序工具类
 * <p>
 * <p>
 * <a href="haibo"><i>View Source</i></a>
 *
 * @author haibo.zhu
 * @version 1.0
 * @since 1.0
 */
public final class CollectionSort {

    private CollectionSort() {

    }

    /**
     * 对list集合进行升序排序
     *
     * @param <T>  类型
     * @param list 待排序的集合
     * @return 排序后的集合
     */
    public static <T> List<T> sortAsc(List<T> list) {
        Ordering<T> ordering = (Ordering<T>)Ordering.natural();
        return ordering.sortedCopy(list);
    }

    /**
     * 对list集合进行降序排序
     *
     * @param <T>  类型
     * @param list 待排序的集合
     * @return 排序后的集合
     */
    public static <T> List<T> sortDesc(List<T> list) {
        Ordering<T> ordering = (Ordering<T>)Ordering.natural().reverse();
        return ordering.sortedCopy(list);
    }

}
