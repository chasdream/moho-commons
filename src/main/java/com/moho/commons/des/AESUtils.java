/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 2017-03-27
 */
package com.moho.commons.des;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 加密解密
 * <p>
 * <p>
 * <a href="haibo"><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public final class AESUtils {

    private AESUtils() {

    }

    public final static String AES = "AES";

    /**
     * 创建并初始化密码器
     *
     * @param key   加解密密钥
     * @param model 加解密类型
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    private static Cipher cipher(byte[] key, int model) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator gen = KeyGenerator.getInstance(AES);
        gen.init(128, new SecureRandom(key));
        SecretKey secretKey = gen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        SecretKeySpec spec = new SecretKeySpec(enCodeFormat, AES);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(AES);
        // 初始化
        cipher.init(model, spec);
        return cipher;
    }

    /**
     * aes加密算法
     *
     * @param data 待加密的数据
     * @param key  加密密钥
     * @return
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = cipher(key, Cipher.ENCRYPT_MODE);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aes解密算法
     *
     * @param data 待解密数据
     * @param key  解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        try {
            Cipher cipher = cipher(key, Cipher.DECRYPT_MODE);
            return cipher.doFinal(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
