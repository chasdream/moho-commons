
package com.moho.commons.secret;

public final class Base64 {

    public static byte[] encode(byte[] bytes) {
        return java.util.Base64.getEncoder().encode(bytes);
    }

    public static byte[] decode(byte[] bytes) {
        return java.util.Base64.getDecoder().decode(bytes);
    }
}
