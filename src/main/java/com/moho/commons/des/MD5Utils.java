/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo.zhu
 * Version: 1.0
 * Date: 2016-09-02
 */
package com.moho.commons.des;

import com.google.common.hash.Hashing;
import org.apache.commons.lang.StringUtils;

import java.security.MessageDigest;

/**
 * MD5加密
 * <p>
 * <p>
 * <a href="haibo"><i>View Source</i></a>
 *
 * @author haibo.zhu
 * @version 1.0
 * @since 1.0
 */
public final class MD5Utils {
    private MD5Utils(){

    }

    /**
     * 加密32位
     * @param source 待加密字符串
     * @return 加密后的字符串
     */
    public static String encode32(String source){
        if(StringUtils.isBlank(source)){
            return source;
        }
        return Hashing.md5().hashBytes(source.getBytes()).toString();
    }

    /**
     * 加密16位
     * @param source 待加密字符串
     * @return 加密后的字符串
     */
    public static String encode16(String source){
        return encode32(source).substring(8, 24);
    }

}
