package com.sample.convert.validation;

import org.sample.definition.FieldDefinition;

public class StubConvertor implements ValidationConvertor {

    @Override
    public String convert(FieldDefinition fieldDefinition) {
        return "";
    }

}
