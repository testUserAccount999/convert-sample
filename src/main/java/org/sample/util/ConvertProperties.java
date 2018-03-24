package org.sample.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConvertProperties {
    private static final String DEFAULT_PROPERTIES = "org.sample.convert.properties";
    private static ConvertProperties me;
    private static Map<String, String> map;

    private ConvertProperties() {
        String path = System.getProperty(DEFAULT_PROPERTIES, DEFAULT_PROPERTIES);
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
            if (inputStream == null) {
                inputStream = new FileInputStream(path);
            }
            Properties properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            Map<String, String> tempMap = new HashMap<>();
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                tempMap.put((String) entry.getKey(), (String) entry.getValue());
            }
            map = Collections.unmodifiableMap(tempMap);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static final void initialize() {
        if (me == null) {
            me = new ConvertProperties();
        }
    }

    public static final String get(String key) {
        initialize();
        return map.get(key);
    }
}
