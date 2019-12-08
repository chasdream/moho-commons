package com.moho.commons.secret;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class RSAUtils {

    private static final String CHARSET = "UTF-8";
    private static final String ALGORITHM_RSA = "RSA";
    private static final String ALGORITHM_RSA_SIGN = "SHA256WithRSA";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;

    public static void main(String[] args) {
        String text = "";
        String key = "";
        String desc = decryptByPrivateKey(text, key, Boolean.FALSE);
        System.out.println("desc = " + desc);
    }

    public static String encryptByPublicKey(String text, String key, boolean gzip) {
        try {
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(key.getBytes(CHARSET)));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key publicKey = keyFactory.generatePublic(x509KeySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] bytes = text.getBytes(CHARSET);
            if (gzip) {
                bytes = GzipUtils.compress(bytes);
            }
            bytes = rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, bytes);
            return new String(Base64.encode(bytes), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("加密出错: key=" + key + ", text=" + text, e);
        }
    }

    public static String decryptByPrivateKey(String text, String key, boolean ungzip) {
        try {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(key.getBytes(CHARSET)));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] bytes = Base64.decode(text.getBytes(CHARSET));
            bytes = rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, bytes);
            if (ungzip) {
                bytes = GzipUtils.uncompress(bytes);
            }
            return new String(bytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("解密出错: key=" + key + ", text=" + text, e);
        }
    }

    public static String sign(String text, String key) {
        try {
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decode(key.getBytes(CHARSET)));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initSign(privateKey);
            signature.update(text.getBytes(CHARSET));

            return new String(Base64.encode(signature.sign()), CHARSET);
        } catch (Exception e) {
            throw new RuntimeException("签名出错: key=" + key + ", text=" + text, e);
        }
    }

    public static boolean verify(String text, String key, String sign) {
        try {
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decode(key.getBytes(CHARSET)));
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
            Signature signature = Signature.getInstance(ALGORITHM_RSA_SIGN);
            signature.initVerify(publicKey);
            signature.update(text.getBytes(CHARSET));
            return signature.verify(Base64.decode(sign.getBytes("utf-8")));
        } catch (Exception e) {
            throw new RuntimeException("验签出错: key=" + key + ", text=" + text, e);
        }
    }

    private static byte[] rsaSplitCodec(Cipher cipher, int opmode, byte[] bytes) throws IOException, IllegalBlockSizeException, BadPaddingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            int maxBlock = opmode == Cipher.DECRYPT_MODE ? MAX_DECRYPT_BLOCK : MAX_ENCRYPT_BLOCK;
            int offSet = 0;
            byte[] buff;
            int i = 0;
            while (bytes.length > offSet) {
                if (bytes.length - offSet > maxBlock) {
                    buff = cipher.doFinal(bytes, offSet, maxBlock);
                } else {
                    buff = cipher.doFinal(bytes, offSet, bytes.length - offSet);
                }
                out.write(buff, 0, buff.length);
                i++;
                offSet = i * maxBlock;
            }
            return out.toByteArray();
        } finally {
            IOUtils.close(out);
        }
    }
}
