/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo.zhu
 * Version: 1.0
 * Date: 2017-02-12
 */
package com.moho.commons.convert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象与map之间转换
 * <p>
 * <p>
 * <a href="haibo"><i>View Source</i></a>
 *
 * @author haibo.zhu
 * @version 1.0
 * @since 1.0
 */
public final class Converts {
    private Converts(){

    }

    /**
     * 对象转成map
     * @param object 参数
     * @return map
     */
    public static Map<String, Object> objectToMap (Object object) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i=1; i<fields.length; i++) {
            String name = fields[i].getName();
            String methodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            map.put(name, object.getClass().getMethod(methodName).invoke(object));
        }
        return map;
    }
}
