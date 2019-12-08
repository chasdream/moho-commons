package com.moho.commons.secret;

import java.io.Closeable;
import java.io.IOException;

public abstract class IOUtils {

    public static void close(Closeable closeable) throws IOException {
        if (closeable == null) {
            return;
        }
        closeable.close();
    }
}
