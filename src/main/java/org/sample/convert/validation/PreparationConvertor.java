package org.sample.convert.validation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.sample.definition.FieldDefinition;
import org.sample.util.FormatUtil;
import org.sample.util.ResourceUtil;

public class PreparationConvertor implements ValidationConvertor {
    private static final String VALIDATOR_NAME = "preparation";
    private static final String FORMAT_PATH = FORMAT_PATH_PREFIX + VALIDATOR_NAME + FORMAT_PATH_SUFFIX;
    private static String format;
    static {
        try {
            format = ResourceUtil.readAll(FORMAT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public PreparationConvertor() {
    }

    @Override
    public ConvertValue convert(FieldDefinition fieldDefinition) {
        Map<String, String> keyValue = new HashMap<>();
        keyValue.put("propertyUpperCamelCase", FormatUtil.toUpperCamelProperty(fieldDefinition.getProperty()));
        return new ConvertValue(format, keyValue);
    }

}
