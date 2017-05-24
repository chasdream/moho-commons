package com.moho.commons.secret;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class BASE64Utils {
    private BASE64Utils() {
        
    }
    
    /**
     * 
     * base64编码
     * @param b 待处理参数
     * @return 编码后的结果
     */
    public static String encoder(byte[] b) {
        if (null != b) {
            String encode = new BASE64Encoder().encode(b);
            return encode;
        }
        return null;
    }
    
    /**
     * 
     * base64解码
     * @param param 待解码参数
     * @return 解码后的结果
     * @throws IOException 异常
     */
    public static byte[] decoder(String param) throws IOException {
        if (null != param) {
            byte[] b = new BASE64Decoder().decodeBuffer(param);
            return b;
        }
        return null;
    }
}
