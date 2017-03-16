/*
 * File Name: @(#)haibo
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 2016-09-02
 */
package com.moho.commons.des;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL字符集编码解码
 * <p>
 * <p>
 * <a href=""><i>View Source</i></a>
 *
 * @author haibo
 * @version 1.0
 * @since 1.0
 */
public final class URLCodeUtils {
    private static final String DEFAULT_CHARACTER = "utf-8";

    private URLCodeUtils() {

    }

    /**
     * 字符集解码
     *
     * @param dataStr 待解密的字符集
     * @return 解密后字符串
     * @throws UnsupportedEncodingException 异常
     */
    public static String decode(String dataStr) throws UnsupportedEncodingException {
        return URLDecoder.decode(dataStr, DEFAULT_CHARACTER);
    }

    /**
     * 字符集编码
     *
     * @param dataStr 待加密的字符集
     * @return 加密后字符串
     * @throws UnsupportedEncodingException 异常
     */
    public static String encode(String dataStr) throws UnsupportedEncodingException {
        return URLEncoder.encode(dataStr, DEFAULT_CHARACTER);
    }
}
