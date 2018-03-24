package com.sample.convert.validation;

import org.sample.definition.FieldDefinition;

public interface ValidationConvertor {
    static final String FORMAT_PATH_PREFIX = "format/validation/";
    static final String FORMAT_PATH_SUFFIX = "Format.txt";
    String convert(FieldDefinition fieldDefinition);
}
