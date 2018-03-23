package org.sample.validator;

import org.sample.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CommonValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonValidator.class);

    private CommonValidator() {
    }

    public static final boolean validateMaxLength(Object object, int max) {
        String value = StringUtils.toStringValue(object);
        boolean isValid = true;
        if (value != null) {
            isValid = (value.length() <= max);
        }
        return isValid;
    }

    public static final boolean validateMinLength(Object object, int min) {
        String value = StringUtils.toStringValue(object);
        boolean isValid = true;
        if (!StringUtils.isBlankOrNull(value)) {
            isValid = (value.length() >= min);
        }
        return isValid;
    }

    public static final boolean validateInteger(Object object) {
        String value = StringUtils.toStringValue(object);
        boolean isValid = true;
        if (!StringUtils.isBlankOrNull(value)) {
            try {
                Integer.valueOf(value);
            } catch (Exception e) {
                LOGGER.debug("error occurred validateInteger.", e);
                isValid = false;
            }
        }
        return isValid;
    }

    public static final boolean validateIntRange(Object object, int min, int max) {
        String value = StringUtils.toStringValue(object);
        boolean isValid = true;
        if (!StringUtils.isBlankOrNull(value)) {
            try {
                int intValue = Integer.valueOf(value);
                isValid = ((intValue >= min) && (intValue <= max));
            } catch (Exception e) {
                LOGGER.debug("error occurred validateIntRange.", e);
                isValid = false;
            }
        }
        return isValid;
    }

    public static final boolean validateDouble(Object object) {
        String value = StringUtils.toStringValue(object);
        boolean isValid = true;
        try {
            if (!StringUtils.isBlankOrNull(value)) {
                Double.valueOf(value);
            }
        } catch (NumberFormatException e) {
            LOGGER.debug("error occurred validateDouble.", e);
            isValid = false;
        }
        return isValid;
    }

    public static final boolean validateRequired(Object object) {
        return !StringUtils.isBlankOrNull(StringUtils.toStringValue(object));
    }

}
