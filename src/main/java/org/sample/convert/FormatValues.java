package org.sample.convert;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.sample.util.ConvertProperties;
import org.sample.util.ResourceUtil;
import org.sample.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class FormatValues {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormatValues.class);
    private static final String CLASS_FORMAT_KEY = "org.sample.convert.format.class";
    private static final String METHOD_FORMAT_KEY = "org.sample.convert.format.validate.method";
    private static final String PRIVATE_METHOD_FORMAT_KEY = "org.sample.convert.format.private.method";
    private static final String IMPORT_CLASSES_KEY = "org.sample.convert.import.classes";
    private static String classFormat;
    private static String validateMethodFormat;
    private static String privateMethodFormat;
    private static Map<String, String> importClasses;

    static {
        try {
            classFormat = ResourceUtil.readAll(ConvertProperties.get(CLASS_FORMAT_KEY));
            validateMethodFormat = ResourceUtil.readAll(ConvertProperties.get(METHOD_FORMAT_KEY));
            privateMethodFormat = ResourceUtil.readAll(ConvertProperties.get(PRIVATE_METHOD_FORMAT_KEY));
            String classes = ConvertProperties.get(IMPORT_CLASSES_KEY);
            Map<String, String> tempMap = new TreeMap<>();
            if (!StringUtils.isBlankOrNull(classes)) {
                for (String className : classes.split(",")) {
                    String[] elements = className.split("\\.");
                    tempMap.put(elements[elements.length - 1], className);
                }
            }
            importClasses = Collections.unmodifiableMap(tempMap);
        } catch (Exception e) {
            LOGGER.error("フォーマットの読み込みで失敗", e);
            throw new RuntimeException(e);
        }
    }

    private FormatValues() {
    }

    public static final String getClassFormat() {
        return classFormat;
    }

    public static final String getValidateMethodFormat() {
        return validateMethodFormat;
    }

    public static final String getPrivateMethodFormat() {
        return privateMethodFormat;
    }
    public static final Map<String, String> getImportClasses(){
        return importClasses;
    }
}
