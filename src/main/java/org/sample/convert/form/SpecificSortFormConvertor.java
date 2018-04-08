package org.sample.convert.form;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

public class SpecificSortFormConvertor implements FormConvertor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpecificSortFormConvertor.class);
    private static final String INDEX_DELIMITER = ":";
    static final String SPECIFIC_SORTED_KEY_PREFIX = "org.sample.convert.sort.order.";
    private ValidationConvertorFactory factory = new DefaultValidationConvertorFactory();
    private Set<String> flagDeclaration = new HashSet<>();

    @Override
    public String convert(FormDefinition formDefinition) {
        String formBeanName = formDefinition.getFormBeanName();
        String sortOrderText = ConvertProperties.get(SPECIFIC_SORTED_KEY_PREFIX + formBeanName);
        if (StringUtils.isBlankOrNull(sortOrderText)) {
            throw new IllegalArgumentException("ソート定義が存在しません。formBeanName=" + formBeanName);
        }
        LOGGER.debug("convert start. form-bean=" + formDefinition.getFormBeanName());
        String[] sortOrder = sortOrderText.split(",");
        StringBuilder executePrivateMethod = new StringBuilder();
        StringBuilder privateMethods = new StringBuilder();
        for (String property : sortOrder) {
            if (property.contains(INDEX_DELIMITER)) {
                String[] elements = property.split(INDEX_DELIMITER);
                property = elements[0];
                Integer index = Integer.valueOf(elements[1]);
                List<FieldDefinition> fieldDefinitions = DefinitionUtil.sortFieldDefinitonList(formDefinition.getFieldDefinitions().get(property));
                FieldDefinition fieldDefinition = fieldDefinitions.get(index);
                String propertyUpperCamelCase = FormatUtil.toUpperCamelProperty(property);
                // 要素ごとに記述していかなければならない
                if (!flagDeclaration.contains(property)) {
                    // フラグ宣言。変換は必ず行われるのでフラグに結果を代入する。
                    String type = fieldDefinition.getVarValue("type");
                    if (StringUtils.isBlankOrNull(type)) {
                        type = "null";
                    } else {
                        type = "\"" + type + "\"";
                    }
                    executePrivateMethod.append("\t\tboolean isValid").append(propertyUpperCamelCase).append(" = CharConv.charConv(form, \"")
                            .append(property).append("\", ").append(type).append(");").append(System.lineSeparator());
                    flagDeclaration.add(property);
                }
                String flagName = "isValid" + propertyUpperCamelCase;
                String flagSetting = flagName + " = false;";
                StringBuilder validateLogic = new StringBuilder();
                for (String depend : fieldDefinition.getDepends()) {
                    if ("preparation".equals(depend)) {
                        // フラグ宣言時に実行しているので無視。
                        continue;
                    }
                    ValidationConvertor validationConvertor = factory.create(depend);
                    String temp = validationConvertor.convert(fieldDefinition).toString();
                    if (!StringUtils.isBlankOrNull(temp)) {
                        // "return;"をフラグの設定に置換
                        String logicTemp = FormatUtil.addIndent(temp.toString().replaceAll("return;", flagSetting));
                        // フラグ判定の分岐
                        validateLogic.append("\t\tif (").append(flagName).append(") {").append(System.lineSeparator());
                        validateLogic.append(logicTemp);
                        validateLogic.append("\t\t}").append(System.lineSeparator());
                    }
                }
                executePrivateMethod.append(validateLogic.toString());
            } else {
                List<FieldDefinition> fieldDefinitions = DefinitionUtil.sortFieldDefinitonList(formDefinition.getFieldDefinitions().get(property));
                Map<String, String> methodKeyValue = new HashMap<>();
                methodKeyValue.put("property", property);
                String propertyUpperCamelCase = FormatUtil.toUpperCamelProperty(property);
                for (int i = 0; i < fieldDefinitions.size(); i++) {
                    FieldDefinition fieldDefinition = fieldDefinitions.get(i);
                    String formSimpleClassName = formDefinition.getFormSimpleClassName();
                    methodKeyValue.put("formSimpleClassName", formSimpleClassName);
                    methodKeyValue.put("propertyUpperCamelCase", propertyUpperCamelCase);
                    StringBuilder validateLogic = new StringBuilder();
                    for (int j = 0; j < fieldDefinition.getDepends().length; j++) {
                        String depend = fieldDefinition.getDepends()[j];
                        ValidationConvertor validationConvertor = factory.create(depend);
                        String temp = validationConvertor.convert(fieldDefinition).toString();
                        if (!StringUtils.isBlankOrNull(temp)) {
                            if (j > 0 && fieldDefinition.getDepends()[j - 1].contains("if") && fieldDefinition.isValidIfCondition()) {
                                validateLogic = FormatUtil.mergeBranchLogic(validateLogic.toString(), temp);
                            } else {
                                validateLogic.append(temp);
                            }
                        }
                    }
                    methodKeyValue.put("validateLogic", validateLogic.toString());
                }
                executePrivateMethod.append("\t\tvalidate").append(propertyUpperCamelCase).append("(form, errors);");
                String methodFormat = FormatValues.getPrivateMethodFormat();
                privateMethods.append(FormatUtil.format(methodFormat, methodKeyValue));
                executePrivateMethod.append(System.lineSeparator());
                privateMethods.append(System.lineSeparator());
            }
        }
        Map<String, String> classKeyValue = new HashMap<>();
        classKeyValue.put("validatorPackage", ConvertProperties.get("org.sample.convert.validator.package"));
        classKeyValue.put("formBeanName", formBeanName);
        String formClassName = formDefinition.getFormClassName();
        classKeyValue.put("formClassName", formClassName);
        String formSimpleClassName = formDefinition.getFormSimpleClassName();
        classKeyValue.put("formSimpleClassName", formSimpleClassName);
        classKeyValue.put("privateMethods", privateMethods.toString());
        classKeyValue.put("executePrivateMethod", executePrivateMethod.toString());
        String validateMethod = FormatUtil.format(FormatValues.getValidateMethodFormat(), classKeyValue);
        classKeyValue.put("validateMethod", validateMethod);
        String validatorString = FormatUtil.format(FormatValues.getClassFormat(), classKeyValue);
        // importの解決
        String importClasses = FormatUtil.resolveImportClass(validatorString);
        classKeyValue.put("importClasses", importClasses);
        validatorString = FormatUtil.format(FormatValues.getClassFormat(), classKeyValue);
        validatorString = new DefaultAdjustmentFactory().create().adjust(formBeanName, validatorString);
        LOGGER.debug("convert end. form-bean=" + formDefinition.getFormBeanName());
        return validatorString;
    }

}
