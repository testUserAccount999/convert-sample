package org.sample.util;

import java.util.Map;

public class StringUtils {
    private StringUtils() {
    }

    public static final String toStringValue(Object object) {
        String value = null;
        if (String.class.isInstance(object)) {
            value = (String) object;
        } else if (object != null) {
            value = object.toString();
        }
        return value;
    }

    public static final String format(String pattern, Map<String, String> keyValue) {
        String formatted = pattern;
        for (Map.Entry<String, String> entry : keyValue.entrySet()) {
            if (entry.getValue() != null) {
                String regex = "\\$\\{" + entry.getKey() + "\\}";
                formatted = formatted.replaceAll(regex, entry.getValue());
            }
        }
        return formatted;
    }

    public static final boolean isBlankOrNull(String value) {
        return ((value == null) || (value.trim().length() == 0));
    }

}
