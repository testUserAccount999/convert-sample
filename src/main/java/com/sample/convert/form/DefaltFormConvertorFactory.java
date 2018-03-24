package com.sample.convert.form;

import org.sample.definition.FormDefinition;
import org.sample.util.DefinitionUtil;

public class DefaltFormConvertorFactory implements FormConvertorFactory {

    @Override
    public FormConvertor create(FormDefinition formDefinition) {
        if(DefinitionUtil.isSimpleDefiniton(formDefinition)) {

        }
        return null;
    }

}
