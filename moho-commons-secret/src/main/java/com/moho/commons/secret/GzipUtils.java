package com.moho.commons.secret;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {

    public static byte[] compress(byte[] bytes) throws IOException {
        GZIPOutputStream gzip = null;
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(bytes);
        } finally {
            IOUtils.close(gzip);
        }
        return out.toByteArray();
    }

    public static byte[] uncompress(byte[] bytes) throws IOException {
        GZIPInputStream in = null;
        PrintStream out = null;
        try {
            in = new GZIPInputStream(new ByteArrayInputStream(bytes));
            ByteArrayOutputStream byteArrayout = new ByteArrayOutputStream();
            out = new PrintStream(byteArrayout);
            byte[] b = new byte[1024];
            int readlen = 0;
            while ((readlen = in.read(b)) != -1) {
                out.write(b, 0, readlen);
            }
            out.flush();
            return byteArrayout.toByteArray();
        } finally {
            IOUtils.close(out);
            IOUtils.close(in);
        }
    }
}
