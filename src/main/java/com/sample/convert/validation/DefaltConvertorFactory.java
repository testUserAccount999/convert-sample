package com.sample.convert.validation;

public class DefaltConvertorFactory implements ValidationConvertorFactory {

    public DefaltConvertorFactory() {
    }

    @Override
    public ValidationConvertor create(String validatorName) {
        ValidationConvertor convertor = null;
        if ("double".equals(validatorName)) {
            convertor = new DoubleConvertor();
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
