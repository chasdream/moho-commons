/* 
 * Copyright (C), 2017-2018
 * File Name: @(#)chasdream
 * Encoding UTF-8
 * Author: chasdream
 * Version: 1.0.0
 * Date: 2018-01-31
 */
package com.moho.commons.secret;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * AES加密解密
 * <p>
 * <p>
 * <a href="chasdream"><i>View Source</i></a>
 *
 * @author chasdream
 * @version 1.0.0
 * @Date: 2018-01-31
 * @since 1.0.0
 */
public class AESUtils {

    private static final String AES = "AES";
    private static final int SECRET_LENGTH = 128;//密钥长度

    private AESUtils() {
    }

    /**
     * aes加密算法
     *
     * @param data 待加密的数据
     * @param key  加密密钥key
     * @return
     */
    public static String encrypt(String data, String key) {
        try {
            Cipher cipher = cipher(key.getBytes("utf-8"), Cipher.ENCRYPT_MODE);
            byte[] bytes = cipher.doFinal(data.getBytes("utf-8"));
            return Base64.getEncoder().encodeToString(bytes);
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * aes解密算法
     *
     * @param data 待解密数据
     * @param key  解密密钥key
     * @return
     */
    public static String decrypt(String data, String key) {
        try {
            Cipher cipher = cipher(key.getBytes("utf-8"), Cipher.DECRYPT_MODE);
            byte[] bytes = cipher.doFinal(Base64.getDecoder().decode(data));
            return new String(bytes, "utf-8");
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成加密密钥并初始化密码器
     *
     * @param key   加解密密钥key
     * @param model 加解密类型
     * @return
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     */
    private static Cipher cipher(byte[] key, int model) throws
            NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator gen = KeyGenerator.getInstance(AES);
        gen.init(SECRET_LENGTH, new SecureRandom(key));
        SecretKey secretKey = gen.generateKey();
        byte[] encoded = secretKey.getEncoded();
        SecretKeySpec spec = new SecretKeySpec(encoded, AES);
        // 创建密码器
        Cipher cipher = Cipher.getInstance(AES);
        // 初始化
        cipher.init(model, spec);
        return cipher;
    }
}
