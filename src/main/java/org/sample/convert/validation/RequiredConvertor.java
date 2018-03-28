package org.sample.convert.validation;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.sample.definition.DefinitionValues;
import org.sample.definition.FieldDefinition;
import org.sample.util.FormatUtil;
import org.sample.util.ResourceUtil;

public class RequiredConvertor implements ValidationConvertor {

    private static final String VALIDATOR_NAME = "required";
    private static final String FORMAT_PATH = FORMAT_PATH_PREFIX + VALIDATOR_NAME + FORMAT_PATH_SUFFIX;
    private static String format;
    static {
        try {
            format = ResourceUtil.readAll(FORMAT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ConvertValue convert(FieldDefinition fieldDefinition) {
        Map<String, String> keyValue = new HashMap<>();
        String property = fieldDefinition.getProperty();
        keyValue.put("property", property);
        String propertyUpperCamelCase = FormatUtil.toUpperCamelProperty(property);
        keyValue.put("propertyUpperCamelCase", propertyUpperCamelCase);
        String msg = DefinitionValues.getValidatorDefinition(VALIDATOR_NAME).getValidator().getMsg();
        if (fieldDefinition.getField().getMsg() != null && VALIDATOR_NAME.equals(fieldDefinition.getField().getMsg().getName())) {
            msg = fieldDefinition.getField().getMsg().getKey();
        }
        keyValue.put("msg", msg);
        keyValue.put("args", FormatUtil.createArgs(fieldDefinition.getArgDefinition(VALIDATOR_NAME)));
        return new ConvertValue(format, keyValue);
    }

}
