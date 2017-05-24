/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.moho.commons;

import com.moho.commons.secret.AESUtils;
import com.moho.commons.secret.BASE64Utils;
import com.moho.commons.secret.MD5Utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import com.moho.commons.json.JsonUtils;

import java.io.IOException;

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
public class SecretTest {

    @Test
    public void testMd5() {
        System.out.println(MD5Utils.md5("加密"));
    }

    @Test
    public void testAes() throws IOException {
        String data = "加密";
        String key = "123456";
        System.out.println("加密前:" + data);
        byte[] aes = AESUtils.encrypt(data.getBytes(), key.getBytes());
        String aesStr = new BASE64Encoder().encode(aes);
        System.out.println("加密后:" + aesStr);
        System.out.println("解密后:" + new String(AESUtils.decrypt(new BASE64Decoder().decodeBuffer(aesStr), key.getBytes())));
    }
    
    @Test
    public void testEncode() {
        System.out.println(BASE64Utils.encoder("123".getBytes()));
    }
    
    @Test
    public void testDecode() throws IOException {
        System.out.println(new String(BASE64Utils.decoder("MTIz")));
    }

}
