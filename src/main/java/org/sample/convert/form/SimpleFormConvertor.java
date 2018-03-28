package org.sample.convert.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sample.convert.FormatValues;
import org.sample.convert.validation.DefaultValidationConvertorFactory;
import org.sample.convert.validation.ValidationConvertor;
import org.sample.convert.validation.ValidationConvertorFactory;
import org.sample.definition.FieldDefinition;
import org.sample.definition.FormDefinition;
import org.sample.util.ConvertProperties;
import org.sample.util.DefinitionUtil;
import org.sample.util.FormatUtil;
import org.sample.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleFormConvertor implements FormConvertor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleFormConvertor.class);

    private ValidationConvertorFactory factory = new DefaultValidationConvertorFactory();

    @Override
    public String convert(FormDefinition formDefinition) {
        LOGGER.debug("convert start. form-bean=" + formDefinition.getFormBeanName());
        StringBuilder executePrivateMethod = new StringBuilder();
        StringBuilder privateMethods = new StringBuilder();
        List<FieldDefinition> fieldDefinitions = DefinitionUtil.sortFieldDefinition(formDefinition);
        for (int i = 0; i < fieldDefinitions.size(); i++) {
            Map<String, String> methodKeyValue = new HashMap<>();
            FieldDefinition fieldDefinition = fieldDefinitions.get(i);
            String property = fieldDefinition.getProperty();
            methodKeyValue.put("property", property);
            String propertyUpperCamelCase = FormatUtil.toUpperCamelProperty(property);
            String formSimpleClassName = formDefinition.getFormSimpleClassName();
            methodKeyValue.put("formSimpleClassName", formSimpleClassName);
            methodKeyValue.put("propertyUpperCamelCase", propertyUpperCamelCase);
            executePrivateMethod.append("\t\tvalidate").append(propertyUpperCamelCase).append("(form, errors);");
            StringBuilder validateLogic = new StringBuilder();
            for (String depend : fieldDefinition.getDepends()) {
                ValidationConvertor validationConvertor = factory.create(depend);
                String temp = validationConvertor.convert(fieldDefinition).toString();
                if (!StringUtils.isBlankOrNull(temp)) {
                    validateLogic.append(temp);
                }
            }
            methodKeyValue.put("validateLogic", validateLogic.toString());
            String methodFormat = FormatValues.getPrivateMethodFormat();
            privateMethods.append(FormatUtil.format(methodFormat, methodKeyValue));
            if (i != fieldDefinitions.size() - 1) {
                executePrivateMethod.append(System.lineSeparator());
                privateMethods.append(System.lineSeparator());
            }
        }
        Map<String, String> classKeyValue = new HashMap<>();
        classKeyValue.put("validatorPackage", ConvertProperties.get("org.sample.convert.validator.package"));
        String formBeanName = formDefinition.getFormBeanName();
        classKeyValue.put("formBeanName", formBeanName);
        String formClassName = formDefinition.getFormClassName();
        classKeyValue.put("formClassName", formClassName);
        String formSimpleClassName = formDefinition.getFormSimpleClassName();
        classKeyValue.put("formSimpleClassName", formSimpleClassName);
        classKeyValue.put("privateMethods", privateMethods.toString());
        classKeyValue.put("executePrivateMethod", executePrivateMethod.toString());
        String validateMethod = FormatUtil.format(FormatValues.getValidateMethodFormat(), classKeyValue);
        classKeyValue.put("validateMethod", validateMethod);
        LOGGER.debug("convert end. form-bean=" + formDefinition.getFormBeanName());
        return FormatUtil.format(FormatValues.getClassFormat(), classKeyValue);
    }

}
