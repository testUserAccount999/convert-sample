package org.sample.convert.form;

import org.sample.definition.FormDefinition;
import org.sample.util.ConvertProperties;
import org.sample.util.DefinitionUtil;
import org.sample.util.StringUtils;

public class DefaultFormConvertorFactory implements FormConvertorFactory {

    @Override
    public FormConvertor create(FormDefinition formDefinition) {
        FormConvertor formConvertor = null;
        if (DefinitionUtil.isSimpleDefiniton(formDefinition)) {
            formConvertor = new SimpleFormConvertor();
        } else if (isSpecificSortDefinition(formDefinition.getFormBeanName())) {
            formConvertor = new SpecificSortFormConvertor();
        } else {
            formConvertor = new DirtyFormConvertor();
        }
        return formConvertor;
    }


    private boolean isSpecificSortDefinition(String formBeanName) {
        String key = SpecificSortFormConvertor.SPECIFIC_SORTED_KEY_PREFIX + formBeanName;
        String sortDefinition = ConvertProperties.get(key);
        return !StringUtils.isBlankOrNull(sortDefinition);
    }

}
