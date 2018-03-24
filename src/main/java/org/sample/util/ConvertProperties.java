package org.sample.util;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertProperties {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertProperties.class);
    private static final String DEFAULT_PROPERTIES = "org.sample.convert.properties";
    private static Map<String, String> map;

    static {
        String path = System.getProperty(DEFAULT_PROPERTIES, DEFAULT_PROPERTIES);
        try {
            LOGGER.info("プロパティファイルを読み込みます。path=" + path);
            InputStream inputStream = ResourceUtil.getInputStream(path);
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
        LOGGER.info("プロパティファイルを読み込みました。path=" + path);
    }

    private ConvertProperties() {
    }

    public static final String get(String key) {
        return map.get(key);
    }
}
