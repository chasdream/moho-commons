/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.moho.commons;

import com.moho.commons.model.Person;
import com.moho.commons.json.JsonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 功能描述
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public class JsonUtilsTest {

    @Test
    public void testToJson() {
        Person person = new Person();
        person.setName("jack");
        person.setAge(20);
        person.setSex("man");
        System.out.println("objcet:" + JsonUtils.toJson(person));

        Map<String, Object> map = new HashMap<>();
        map.put("num1", "123");
        map.put("num2", 2);
        map.put("session", "oekwnfo12lsdoe");
        System.out.println("map:" + JsonUtils.toJson(map));
    }

    @Test
    public void testToObject() {
        String jsonStr = "{\"name\":\"jack\",\"sex\":\"man\",\"age\":20}";
        Person person = JsonUtils.toObject(jsonStr, Person.class);
        System.out.println(person.toString());
    }
    
    @Test
    public void test() {
        System.out.println("f-test...解决冲突...");
    }
    /**
     * 冲突文件解决方案
     */
    
    
    public void feature() {
        System.out.println(".......");
    }
}
