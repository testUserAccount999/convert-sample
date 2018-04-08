package org.sample.convert.form;

import java.util.ArrayList;
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

public class DirtyFormConvertor implements FormConvertor {
    private ValidationConvertorFactory factory = new DefaultValidationConvertorFactory();
    private Set<String> flagDeclaration = new HashSet<>();

    @Override
    public String convert(FormDefinition formDefinition) {
        List<FieldDefinition> tempList = new ArrayList<>();
        for (Map.Entry<String, List<FieldDefinition>> entry : formDefinition.getFieldDefinitions().entrySet()) {
            tempList.addAll(entry.getValue());
        }
        StringBuilder executePrivateMethod = new StringBuilder();
        StringBuilder privateMethods = new StringBuilder();
        for (FieldDefinition fieldDefinition : DefinitionUtil.sortFieldDefinitonList(tempList)) {
            String property = fieldDefinition.getProperty();
            String propertyUpperCamelCase = FormatUtil.toUpperCamelProperty(property);
            if (isSimgleFieldDefinition(property, tempList)) {
                // 定義が一つであればprivateメソッドに抜き出せる
                Map<String, String> methodKeyValue = new HashMap<>();
                methodKeyValue.put("property", property);
                String formSimpleClassName = formDefinition.getFormSimpleClassName();
                methodKeyValue.put("formSimpleClassName", formSimpleClassName);
                methodKeyValue.put("propertyUpperCamelCase", propertyUpperCamelCase);
                executePrivateMethod.append("\t\tvalidate").append(propertyUpperCamelCase).append("(form, errors);");
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
                String methodFormat = FormatValues.getPrivateMethodFormat();
                privateMethods.append(FormatUtil.format(methodFormat, methodKeyValue));
            } else {
                if (!flagDeclaration.contains(property)) {
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
        String validatorString = FormatUtil.format(FormatValues.getClassFormat(), classKeyValue);
        // importの解決
        String importClasses = FormatUtil.resolveImportClass(validatorString);
        classKeyValue.put("importClasses", importClasses);
        validatorString = FormatUtil.format(FormatValues.getClassFormat(), classKeyValue);
        validatorString = new DefaultAdjustmentFactory().create().adjust(formBeanName, validatorString);
        return validatorString;
    }

    private boolean isSimgleFieldDefinition(String property, List<FieldDefinition> allFeildDefintion) {
        int count = 0;
        for (FieldDefinition fieldDefinition : allFeildDefintion) {
            if (property.equals(fieldDefinition.getProperty())) {
                count += 1;
            }
            if (count > 1) {
                return false;
            }
        }
        return true;
    }

}
