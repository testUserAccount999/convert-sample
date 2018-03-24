package org.sample.util;

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

    public static final boolean isBlankOrNull(String value) {
        return ((value == null) || (value.trim().length() == 0));
    }

}
