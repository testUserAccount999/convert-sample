package org.sample.convert.form;

import org.sample.definition.FormDefinition;

public interface FormConvertorFactory {
    FormConvertor create(FormDefinition formDefinition);
}
