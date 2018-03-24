package com.sample.convert;

import org.sample.util.ConvertProperties;
import org.sample.util.ResourceUtil;

public final class FormatValues {

    private static final String CLASS_FORMAT_KEY = "org.sample.convert.format.class";
    private static final String METHOD_FORMAT_KEY = "org.sample.convert.format.validate.method";
    private static final String PRIVATE_METHOD_FORMAT_KEY = "org.sample.convert.format.private.method";
    private static FormatValues me;
    private static String classFormat;
    private static String validateMethodFormat;
    private static String privateMethodFormat;

    private FormatValues() {
    }

    private static void initialize() {
        if(me != null) {
            return;
        }
        try {
            classFormat = ResourceUtil.readAll(ConvertProperties.get(CLASS_FORMAT_KEY));
            validateMethodFormat = ResourceUtil.readAll(ConvertProperties.get(METHOD_FORMAT_KEY));
            privateMethodFormat = ResourceUtil.readAll(ConvertProperties.get(PRIVATE_METHOD_FORMAT_KEY));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        me = new FormatValues();
    }

    public static final String getClassFormat() {
        initialize();
        return classFormat;
    }

    public static final String getValidateMethodFormat() {
        initialize();
        return validateMethodFormat;
    }

    public static final String getPrivateMethodFormat() {
        initialize();
        return privateMethodFormat;
    }
}
