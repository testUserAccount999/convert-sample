package org.sample.convert.form;

import org.sample.definition.FormDefinition;
import org.sample.util.DefinitionUtil;

public class DefaultFormConvertorFactory implements FormConvertorFactory {

    @Override
    public FormConvertor create(FormDefinition formDefinition) {
        FormConvertor formConvertor = null;
        if(DefinitionUtil.isSimpleDefiniton(formDefinition)) {
            formConvertor = new SimpleFormConvertor();
        }
        if(formConvertor == null) {
            // TODO スタブ
            formConvertor = new FormConvertor() {
                @Override
                public String convert(FormDefinition formDefinition) {
                    return "";
                }
            };
        }
        return formConvertor;
    }

}
