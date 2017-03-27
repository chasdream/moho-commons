/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.moho.commons;

import com.moho.commons.des.AESUtils;
import com.moho.commons.des.MD5Utils;
import com.moho.commons.json.JsonUtils;
import org.junit.Test;

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
public class MD5UtilsTest {

    @Test
    public void testMd5() {
        System.out.println(MD5Utils.md5("加密"));
    }

    @Test
    public void testAes() {
        String data = "加密";
        String key = "123456";
        System.out.println("加密前:" + data);
        byte[] aes = AESUtils.encrypt(data.getBytes(), key.getBytes());
        System.out.println("加密后:" + JsonUtils.toJson(aes));
        System.out.println("解密后:" + new String(AESUtils.decrypt(aes, key.getBytes())));
    }

}
