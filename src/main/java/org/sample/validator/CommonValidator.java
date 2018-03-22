package org.sample.validator;

public class CommonValidator {

    private CommonValidator() {
    }

    public static final boolean validateMaxLength(Object object, int max) {
        String value = toString(object);
        boolean isValid = true;
        if (value != null) {
            isValid = (value.length() <= max);
        }
        return isValid;

    }

    public static final boolean validateMinLength(Object object, int min) {
        String value = toString(object);
        boolean isValid = true;
        if (!isBlankOrNull(value)) {
            isValid = (value.length() >= min);
        }
        return isValid;
    }

    public static final boolean validateIntRange(Object object, int min, int max) {
        String value = toString(object);
        boolean isValid = true;
        if (!isBlankOrNull(value)) {
            try {
                int intValue = Integer.valueOf(value);
                isValid = ((intValue >= min) && (intValue <= max));
            } catch (Exception e) {
                isValid = false;
            }
        }
        return isValid;
    }

    public static final boolean validateDouble(Object object) {
        String value = toString(object);
        boolean isValid = true;
        try {
            if (!isBlankOrNull(value)) {
                Double.valueOf(value);
            }
        } catch (NumberFormatException | NullPointerException e) {
            isValid = false;
        }
        return isValid;
    }

    public static final boolean validateRequired(Object object) {
        return !isBlankOrNull(toString(object));
    }

    public static final boolean isBlankOrNull(String value) {
        return ((value == null) || (value.trim().length() == 0));
    }

    private static final String toString(Object object) {
        String value = null;
        if (isString(object)) {
            value = (String) object;
        } else if (object != null) {
            value = object.toString();
        }
        return value;
    }

    private static final boolean isString(Object object) {
        return String.class.isInstance(object);
    }
}
