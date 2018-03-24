package com.sample.convert;

import java.util.Map;

import org.sample.definition.ValidatorDefinition;

public class DefaltConvertorFactory implements ConvertorFactory {

    private Map<String, ValidatorDefinition> validatorValidatorDefinitions;

    public DefaltConvertorFactory(Map<String, ValidatorDefinition> validatorValidatorDefinitions) {
        this.validatorValidatorDefinitions = validatorValidatorDefinitions;
    }

    @Override
    public Convertor create(String validatorName) {
        Convertor convertor = null;
        if ("double".equals(validatorName)) {
            convertor = new DoubleConvertor(validatorValidatorDefinitions.get(validatorName));
        } else if ("intRange".equals(validatorName)) {
        } else if ("maxlength".equals(validatorName)) {
        } else if ("minlength".equals(validatorName)) {
        } else if ("required".equals(validatorName)) {
        } else if ("requiredif".equals(validatorName)) {
        } else if ("preparation".equals(validatorName)) {
            convertor = new PreparationConvertor();
        }
        if (convertor == null) {
            convertor = new StubConvertor();
        }
        return convertor;
    }

}
