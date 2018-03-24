package com.sample.convert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.sample.definition.FieldDefinition;
import org.sample.util.FormatUtil;
import org.sample.util.ResourceUtil;

public class PreparationConvertor implements Convertor {
    private static final String VALIDATOR_NAME = "preparation";
    private static final String FORMAT_PATH = "format/" + VALIDATOR_NAME + "Format.txt";
    private String format;

    public PreparationConvertor() {
        try {
            format = ResourceUtil.readAll(FORMAT_PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String convert(FieldDefinition fieldDefinition) {
        Map<String, String> keyValue = new HashMap<>();
        return FormatUtil.format(format, keyValue);
    }

}
