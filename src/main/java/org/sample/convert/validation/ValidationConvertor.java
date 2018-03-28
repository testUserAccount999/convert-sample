package org.sample.convert.validation;

import org.sample.definition.FieldDefinition;

/**
 * ValidationConvertorの実装クラスは変換済みの文字列を返却すること。
 */
public interface ValidationConvertor {
    static final String FORMAT_PATH_PREFIX = "format/validation/";
    static final String FORMAT_PATH_SUFFIX = "Format.txt";

    ConvertValue convert(FieldDefinition fieldDefinition);

}
