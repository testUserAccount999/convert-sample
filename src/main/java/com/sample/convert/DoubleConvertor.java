package com.sample.convert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.sample.definition.FieldDefinition;
import org.sample.definition.ValidatorDefinition;
import org.sample.util.FormatUtil;
import org.sample.util.ResourceUtil;

public class DoubleConvertor implements Convertor {
    private static final String VALIDATOR_NAME = "double";
    private static final String FORMAT_PATH = "format/doubleFormat.txt";
    private ValidatorDefinition validatorDefinition;
    private String format;

    public DoubleConvertor(ValidatorDefinition validatorDefinition) {
        try {
            format = ResourceUtil.readAll(FORMAT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.validatorDefinition = validatorDefinition;
    }

    @Override
    public String convert(FieldDefinition fieldDefinition) {
        Map<String, String> keyValue = new HashMap<>();
        String property = fieldDefinition.getProperty();
        keyValue.put("property", property);
        String propertyUpperCamelCase = FormatUtil.toUpperCamelProperty(property);
        keyValue.put("propertyUpperCamelCase", propertyUpperCamelCase);
        String msg = validatorDefinition.getValidator().getMsg();
        if (VALIDATOR_NAME.equals(fieldDefinition.getField().getMsg().getName())) {
            msg = fieldDefinition.getField().getMsg().getKey();
        }
        keyValue.put("msg", msg);

        return FormatUtil.format(format, keyValue);
    }

}
