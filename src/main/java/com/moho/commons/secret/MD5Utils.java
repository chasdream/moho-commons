/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 2016-09-02
 */
package com.moho.commons.secret;

import com.google.common.hash.Hashing;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * <p>
 * <p>
 * <a href="haibo"><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public final class MD5Utils {
    private MD5Utils() {

    }

    /**
     * 加密32位
     *
     * @param source 待加密字符串
     * @return 加密后的字符串
     */
    public static String encode32(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        return Hashing.md5().hashBytes(source.getBytes()).toString();
    }

    /**
     * 加密16位
     *
     * @param source 待加密字符串
     * @return 加密后的字符串
     */
    public static String encode16(String source) {
        return encode32(source).substring(8, 24);
    }

    /**
     * md5加密算法
     *
     * @param param 待加密字符串
     * @return 加密后32位字符串
     */
    public static String md5(String param) {
        String md5 = new String();
        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(param.getBytes());
            byte b[] = mDigest.digest();
            int i;
            StringBuffer buf = new StringBuffer();
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

}
