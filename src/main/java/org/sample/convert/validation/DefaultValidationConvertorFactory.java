package org.sample.convert.validation;

import org.sample.definition.FieldDefinition;

public class DefaultValidationConvertorFactory implements ValidationConvertorFactory {

    public DefaultValidationConvertorFactory() {
    }

    @Override
    public ValidationConvertor create(String validatorName) {
        ValidationConvertor convertor = null;
        if ("double".equals(validatorName)) {
            convertor = new DoubleConvertor();
        } else if ("integer".equals(validatorName)) {
            convertor = new IntegerConvertor();
        } else if ("intRange".equals(validatorName)) {
            convertor = new IntRangeConvertor();
        } else if ("maxlength".equals(validatorName)) {
            convertor = new MaxlengthConvertor();
        } else if ("minlength".equals(validatorName)) {
            convertor = new MinlengthConvertor();
        } else if ("required".equals(validatorName)) {
            convertor = new RequiredConvertor();
        } else if ("requiredif".equals(validatorName)) {
            convertor = new RequiredifConvertor();
        } else if ("preparation".equals(validatorName)) {
            convertor = new PreparationConvertor();
        }
        if (convertor == null) {
            convertor = new ValidationConvertor() {
                @Override
                public String convert(FieldDefinition fieldDefinition) {
                    return "";
                }
            };
        }
        return convertor;
    }

}
