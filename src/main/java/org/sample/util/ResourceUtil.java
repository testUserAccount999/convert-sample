package org.sample.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public final class ResourceUtil {

    private ResourceUtil() {
    }

    public static String readAll(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getInputStream(path), Charset.forName("UTF-8")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public static InputStream getInputStream(String path) throws IOException {
        InputStream inputStream = ResourceUtil.class.getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            inputStream = new FileInputStream(path);
        }
        return inputStream;
    }
}
