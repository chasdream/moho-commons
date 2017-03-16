/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo.zhu
 * Version: 1.0
 * Date: 
 */
package com.moho.commons;

import com.moho.commons.convert.Converts;
import com.moho.commons.des.URLCodeUtils;
import com.moho.commons.json.JsonUtils;
import com.moho.commons.model.Dog;
import com.moho.commons.model.Person;
import com.moho.commons.sort.CollectionSort;
import com.moho.commons.des.MD5Utils;
import com.moho.commons.sort.SortUtils;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo.zhu
 * @version 1.0
 * @since 1.0
 */
public class CollectionSortTest {

    @Test
    public void testSortAsc() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(1);
        list.add(0);
        list.add(6);
        list.add(9);
        list.add(2);
        list.add(8);
        System.out.println("asc:" + CollectionSort.sortAsc(list));
        System.out.println("desc:" + CollectionSort.sortDesc(list));
    }

    @Test
    public void testSortStr() {
        Map<String, Object> map = new HashMap<>();
        map.put("num1", "123");
        map.put("num2", 2);
        map.put("session", "oekwnfo12lsdoe");
        String jsonStr = "{\"name\":\"jack\",\"sex\":\"man\",\"age\":20}";
        System.out.println(SortUtils.sortStr(jsonStr));
    }

    @Test
    public void testEncode() {
        String str = "123456";
        System.out.println(MD5Utils.encode32(str));
        System.out.println(MD5Utils.encode16(str));
    }

    @Test
    public void testDecode() throws UnsupportedEncodingException {
        String str = "我是谁-123";
        String decode = URLCodeUtils.encode(str);
        System.out.println(decode);
        System.out.println(URLCodeUtils.decode(decode));
    }

    @Test
    public void testObjectToMap() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Person person = new Person();
        person.setName("jack");
        person.setAge(20);
        person.setSex("man");
        Dog dog = new Dog();
        dog.setDogName("bldog");
        dog.setDogAge(10);
        person.setDog(dog);

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(1);
        list.add(0);
        list.add(6);
        person.setList(list);
        System.out.println(JsonUtils.toJson(Converts.objectToMap(person)));
    }
}
