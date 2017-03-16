/* 
 * Copyright (C), 2015-2016
 * File Name: @(#)
 * Encoding UTF-8
 * Author: haibo
 * Version: 1.0
 * Date: 
 */
package com.moho.commons;

import com.moho.commons.des.MD5Utils;
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

}
