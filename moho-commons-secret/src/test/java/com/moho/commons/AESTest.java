package com.moho.commons;

import com.moho.commons.secret.AESUtils;
import junit.framework.TestCase;

import java.io.UnsupportedEncodingException;

/**
 * Unit test for simple App.
 */
public class AESTest extends TestCase {

    public void testEncrypt() throws UnsupportedEncodingException {
        String str = AESUtils.encrypt("123", "qwe");
        System.out.println("加密：" + str);
    }

    public void testDecrypt() {
        System.out.println("解密：" + AESUtils.decrypt("+2rRF+UqYL12jiM/EGC6gA==", "qwe"));
    }
}
