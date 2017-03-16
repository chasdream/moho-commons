/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 2016-09-06
 */
package com.moho.commons.sort;

import com.moho.commons.json.JsonUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * 排序工具类
 * <p>
 * <p>
 * <a href="haibo"><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public final class SortUtils {

    /**
     * 将data的参数进行首字母排序后  重装字符串
     *
     * @param data 请求参数中的业务参数
     * @return 重新排序后字符串，若为null表示排序失败
     */
    public static String sortStr(String data) {
        if (StringUtils.isBlank(data)) {
            return null;
        }
        // JSON字符串转成map
        Map<String, Object> map = JsonUtils.toMap(data, String.class, Object.class);
        if (map == null) {
            return null;
        }
        TreeMap<String, Object> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override public int compare(String str1, String str2) {
                return str1.compareTo(str2);
            }
        });
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        for (Map.Entry<String, Object> entry : set) {
            Object value = entry.getValue();
            if (value != null) {
                    /* 过滤掉list对象 */
                if (value instanceof List) {
                    continue;
                }
                if (value instanceof String) {
                    treeMap.put(entry.getKey(), value);
                } else if (value instanceof Integer) {
                    treeMap.put(entry.getKey(), value);
                } else {
                    treeMap.put(entry.getKey(), JsonUtils.toJson(value));
                }
            }
        }
        Set<Map.Entry<String, Object>> treeSet = treeMap.entrySet();
        for (Map.Entry<String, Object> entry : treeSet) {
            sb.append(entry.getKey()).append(entry.getValue());
        }
        return sb.toString();
    }

}
