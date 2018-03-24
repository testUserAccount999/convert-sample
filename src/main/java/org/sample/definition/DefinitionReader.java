package org.sample.definition;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.sample.struts.config.FormBean;
import org.sample.struts.config.StrutsConfig;
import org.sample.struts.rule.Global;
import org.sample.struts.rule.Validator;
import org.sample.struts.util.StrutsXmlParser;
import org.sample.struts.validation.Field;
import org.sample.struts.validation.Form;
import org.sample.struts.validation.Formset;
import org.sample.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class DefinitionReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefinitionReader.class);
    private String strutsConfig;
    private String[] validationRules;
    private String[] validationXmls;

    public DefinitionReader(String strutsConfig, String[] validationXmls, String[] validationRules) {
        this.strutsConfig = strutsConfig;
        this.validationXmls = validationXmls;
        this.validationRules = validationRules;
    }

    public Map<String, FormDefinition> readFormDefinition() throws IOException {
        Map<String, FormDefinition> map = new HashMap<>();
        StrutsConfig config = null;
        try (InputStream inputStreamStrutsConfig = ResourceUtil.getInputStream(strutsConfig)) {
            config = StrutsXmlParser.parseStrutsConfig(inputStreamStrutsConfig);
        } catch (Exception e) {
            // エラーハンドリングが面倒なのでIOExceptionにラップ
            throw new IOException("struts-config読み込みでエラー", e);
        }
        Map<String, String> formBeans = readFormBeans(config);
        for (String xml : validationXmls) {
            for (Map.Entry<String, FormDefinition> entry : readFormDefinition(xml, formBeans).entrySet()) {
                if (map.containsKey(entry.getKey())) {
                    LOGGER.info("Form定義重複 : " + entry.getKey() + " を上書きます。");
                }
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    public Map<String, ValidatorDefinition> readValidatorDefinition() throws IOException {
        Map<String, ValidatorDefinition> map = new HashMap<>();
        for (String xml : validationRules) {
            for (Map.Entry<String, ValidatorDefinition> entry : readValidatorDefinition(xml).entrySet()) {
                if (map.containsKey(entry.getKey())) {
                    LOGGER.info("Validator定義重複 : " + entry.getKey() + " を上書きます。");
                }
                map.put(entry.getKey(), entry.getValue());
            }
        }
        return map;
    }

    private Map<String, FormDefinition> readFormDefinition(String xml, Map<String, String> formBeans) throws IOException {
        Map<String, FormDefinition> map = new HashMap<>();
        org.sample.struts.validation.FormValidation formValidation = null;
        try (InputStream inputStreamValidationXml = ResourceUtil.getInputStream(xml);) {
            formValidation = StrutsXmlParser.parseValidation(inputStreamValidationXml);
        } catch (Exception e) {
            // エラーハンドリングが面倒なのでIOExceptionにラップ
            throw new IOException("Form定義読み込みでエラー", e);
        }
        for (Formset formSet : formValidation.getFormset()) {
            for (Form form : formSet.getForm()) {
                String formBeanName = form.getName();
                if (formBeans.containsKey(formBeanName)) {
                    String formClassName = formBeans.get(formBeanName);
                    FormDefinition formDefinition = new FormDefinition(formBeanName, formClassName);
                    int definitionOrder = 0;
                    for (Field field : form.getField()) {
                        FieldDefinition fieldDefinition = new FieldDefinition(field, String.format("%03d", definitionOrder++));
                        formDefinition.addFieldDefinition(fieldDefinition);
                        if (!fieldDefinition.isValidIfCondition()) {
                            LOGGER.info("form-bean : " + formBeanName + " の property : " + field.getProperty() + " には有効にはならないValidatorの指定があります。");
                        }
                    }
                    LOGGER.debug("form-bean : " + formBeanName + " の定義を読み込みました。");
                    map.put(formBeanName, formDefinition);
                } else {
                    LOGGER.info("form-beansにform-bean : " + formBeanName + " は存在しません。スキップします。");
                }
            }
        }
        return map;
    }

    private Map<String, ValidatorDefinition> readValidatorDefinition(String xml) throws IOException {
        Map<String, ValidatorDefinition> map = new HashMap<>();
        org.sample.struts.rule.FormValidation formValidation = null;
        try (InputStream inputStreamValidationRule = ResourceUtil.getInputStream(xml)) {
            formValidation = StrutsXmlParser.parseValidationRule(inputStreamValidationRule);
        } catch (ParserConfigurationException | SAXException | JAXBException e) {
            // エラーハンドリングが面倒なのでIOExceptionにラップ
            throw new IOException("Validator定義読み込みでエラー", e);
        }
        for (Global global : formValidation.getGlobal()) {
            for (Validator validator : global.getValidator()) {
                ValidatorDefinition validatorDefinition = new ValidatorDefinition(validator);
                if (map.containsKey(validatorDefinition.getValidatorName())) {
                    LOGGER.info("Validator定義重複 : " + validatorDefinition.getValidatorName() + " を上書きます。");
                }
                map.put(validatorDefinition.getValidatorName(), validatorDefinition);
            }
        }
        return map;
    }

    private Map<String, String> readFormBeans(StrutsConfig strutsConfig) {
        Map<String, String> map = new HashMap<>();
        for (FormBean formBean : strutsConfig.getFormBeans().getFormBean()) {
            map.put(formBean.getName(), formBean.getType());
        }
        return map;
    }

}
