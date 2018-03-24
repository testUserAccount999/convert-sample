package org.sample.convert.validation;

import org.sample.definition.FieldDefinition;
import org.sample.util.FormatUtil;

public class RequiredifConvertor implements ValidationConvertor {

    private static final String FORMAT_PREFIX = "\t\\tif (\\$\\{condition\\}) {" + System.lineSeparator();
    private static final String FORMAT_SUFFIX = "\t\\}";

    @Override
    public String convert(FieldDefinition fieldDefinition) {

        if(!fieldDefinition.isValidIfCondition()) {
            return "";
        }
        String requiredPart = new RequiredConvertor().convert(fieldDefinition);
        requiredPart = FormatUtil.addIndent(requiredPart);

        return requiredPart;
    }

}
