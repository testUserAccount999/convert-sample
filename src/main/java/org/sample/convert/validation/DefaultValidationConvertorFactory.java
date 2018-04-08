package org.sample.convert.validation;

import org.sample.definition.FieldDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultValidationConvertorFactory implements ValidationConvertorFactory {

    private static final String CONVERTOR_PREFIX = "org.sample.convert.validation.";
    private static final String CONVERTOR_SUFFIX = "Convertor";
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultValidationConvertorFactory.class);

    @Override
    public ValidationConvertor create(String validatorName) {
        ValidationConvertor convertor = null;
        String convertorClassName = CONVERTOR_PREFIX + validatorName.substring(0, 1).toUpperCase() + validatorName.substring(1) + CONVERTOR_SUFFIX;
        try {
            Class<?> clazz = Class.forName(convertorClassName);
            convertor = (ValidationConvertor) clazz.newInstance();
        } catch (Exception e) {
            LOGGER.error("Convertorが取得できません。validataorName=" + validatorName, e);
            convertor = new ValidationConvertor() {
                @Override
                public ConvertValue convert(FieldDefinition fieldDefinition) {
                    return new ConvertValue();
                }
            };
        }
        return convertor;
    }

}
