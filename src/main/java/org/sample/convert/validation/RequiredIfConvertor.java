package org.sample.convert.validation;

import java.util.HashMap;
import java.util.Map;

import org.sample.definition.FieldDefinition;
import org.sample.util.FormatUtil;

public class RequiredIfConvertor implements ValidationConvertor {

    ValidationConvertorFactory factory = new DefaultValidationConvertorFactory();

    private static final String FORMAT_PREFIX = "\t\tif (${condition}) {" + System.lineSeparator();
    private static final String FORMAT_SUFFIX = "\t\t}" + System.lineSeparator();
    private String validatorName;

    public RequiredIfConvertor(String validatorName) {
        this.validatorName = validatorName;
    }

    @Override
    public ConvertValue convert(FieldDefinition fieldDefinition) {
        if (!fieldDefinition.isValidIfCondition()) {
            return "";
        }

        for (int i = 0; i < fieldDefinition.getDepends().length; i++) {
            String depend = fieldDefinition.getDepends()[i];
            if (validatorName.equals(depend)) {

            }
        }
        String requiredPart = factory.create(validatorName.substring(0, validatorName.length() - 2)).convert(fieldDefinition);
        if ("".equals(requiredPart)) {
            return "";
        }
        requiredPart = FormatUtil.addIndent(requiredPart);
        String condition = FormatUtil.createCondition(fieldDefinition.getIfCondition());
        Map<String, String> keyValue = new HashMap<>();
        keyValue.put("condition", condition);
        String prefix = FormatUtil.format(FORMAT_PREFIX, keyValue);
        return prefix + requiredPart + FORMAT_SUFFIX;
    }

}
