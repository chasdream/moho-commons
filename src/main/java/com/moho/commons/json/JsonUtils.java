/* 
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo.zhu
 * Version: 1.0
 * Date: 2016-09-02
 */
package com.moho.commons.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Map;

/**
 * json转换工具类
 * <p>
 * <p>
 * <a href="haibo"><i>View Source</i></a>
 *
 * @author haibo.zhu
 * @version 1.0
 * @since 1.0
 */
public final class JsonUtils {
    private JsonUtils() {

    }

    private static ObjectMapper toMapper(JsonInclude.Include include){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(include);//配置mapper忽略空属性
        return objectMapper;
    }

    /**
     * 将对象转换为字符串
     *
     * @param object 待转换的对象
     * @return json字符串
     */
    public static String toJson(Object object) {
        return toJson(JsonInclude.Include.NON_EMPTY, object);
    }

    /**
     * 将对象转换为字符串
     *
     * @param include include
     * @param object  待转换的对象
     * @return json字符串
     */
    public static String toJson(JsonInclude.Include include, Object object) {
        try {
            ObjectMapper objectMapper = toMapper(include);
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json字符串转换为对象
     *
     * @param jsonStr json字符串
     * @param clazz   对象
     * @param <T>     参数
     * @return 指定对象
     */
    public static <T> T toObject(String jsonStr, Class<T> clazz) {
        return toObject(JsonInclude.Include.NON_EMPTY, jsonStr, clazz);
    }

    /**
     * json字符串转换为对象
     *
     * @param include include
     * @param jsonStr json字符串
     * @param clazz   对象
     * @param <T>     参数
     * @return 指定对象
     */
    public static <T> T toObject(JsonInclude.Include include, String jsonStr, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = toMapper(include);
            //当反序列化json时，未知属性会引起的反序列化被打断，这里我们禁用未知属性打断反序列化功能;
            //例如json里有10个属性，而我们的bean中只定义了2个属性，其它8个属性将被忽略
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper.readValue(jsonStr, TypeFactory.defaultInstance().constructType(clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将Map集合转换为json字符串
     *
     * @param map map集合
     * @param <k> 键
     * @param <v> 值
     * @return json字符串
     */
    public static <k, v> String toJson(Map<k, v> map) {
        return toJson(JsonInclude.Include.NON_EMPTY, map);
    }

    /**
     * 将Map集合转换为json字符串
     *
     * @param include include
     * @param map     map集合
     * @param <k>     键
     * @param <v>     值
     * @return json字符串
     */
    public static <k, v> String toJson(JsonInclude.Include include, Map<k, v> map) {
        try {
            ObjectMapper objectMapper = toMapper(include);
            return objectMapper.writeValueAsString(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json字符串转成map
     *
     * @param jsonStr json字符串
     * @param kClass  健对象
     * @param vClass  值对象
     * @param <k>     k
     * @param <v>     v
     * @return 指定的集合对象
     */
    public static <k, v> Map<k, v> toMap(String jsonStr, Class<k> kClass, Class<v> vClass) {
        return toMap(JsonInclude.Include.NON_EMPTY, jsonStr, kClass, vClass);
    }

    /**
     * 将json字符串转成map
     *
     * @param include include
     * @param jsonStr json字符串
     * @param kClass  健对象
     * @param vClass  值对象
     * @param <k>     k
     * @param <v>     v
     * @return 指定的集合对象
     */
    public static <k, v> Map<k, v> toMap(JsonInclude.Include include, String jsonStr, Class<k> kClass,
            Class<v> vClass) {
        try {
            ObjectMapper objectMapper = toMapper(include);
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            return objectMapper
                    .readValue(jsonStr, TypeFactory.defaultInstance().constructMapType(Map.class, kClass, vClass));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
