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
     * @param b
     * @return
     */
    public static String encoder(byte[] b) {
        if (null != b) {
            String encode = new BASE64Encoder().encode(b);
            return encode;
        }
        return null;
    }
    
    public static byte[] decoder(String param) throws IOException {
        if (null != param) {
            byte[] b = new BASE64Decoder().decodeBuffer(param);
            return b;
        }
        return null;
    }
}
