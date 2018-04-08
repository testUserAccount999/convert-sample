package org.sample.convert.validation;

import org.sample.definition.FieldDefinition;

public interface ValidationConvertor {
    static final String FORMAT_PATH_PREFIX = "format/validation/";
    static final String FORMAT_PATH_SUFFIX = "Format.txt";
    static final String UTIL_KEY_PREFIX = "org.sample.convert.util.";
    ConvertValue convert(FieldDefinition fieldDefinition);
}
