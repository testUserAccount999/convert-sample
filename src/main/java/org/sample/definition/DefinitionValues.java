package org.sample.definition;

import java.util.Collections;
import java.util.Map;

import org.sample.util.ConvertProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DefinitionValues {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefinitionValues.class);
    private static final String CONFIG_KEY = "org.sample.convert.strut.config";
    private static final String VALIDATION_KEY = "org.sample.convert.strut.validation";
    private static final String RULE_KEY = "org.sample.convert.strut.validation.rule";
    private static final String COMMA = ",";
    private static Map<String, ValidatorDefinition> validatorDefinitions;
    private static Map<String, FormDefinition> formDefinitions;

    static {
        String config = System.getProperty(CONFIG_KEY, ConvertProperties.get(CONFIG_KEY));
        String validation = System.getProperty(VALIDATION_KEY, ConvertProperties.get(VALIDATION_KEY));
        String rule = System.getProperty(RULE_KEY, ConvertProperties.get(RULE_KEY));
        try {
            DefinitionReader reader = new DefinitionReader(config, validation.split(COMMA), rule.split(COMMA));
            formDefinitions = Collections.unmodifiableMap(reader.readFormDefinition());
            validatorDefinitions = Collections.unmodifiableMap(reader.readValidatorDefinition());
        } catch (Exception e) {
            LOGGER.error("strutsの設定ファイル読み込みでエラー", e);
            throw new RuntimeException(e);
        }
    }

    private DefinitionValues() {
    }

    public static Map<String, ValidatorDefinition> getValidatorDefinitions() {
        return validatorDefinitions;
    }

    public static ValidatorDefinition getValidatorDefinition(String depend) {
        return validatorDefinitions.get(depend);
    }

    public static Map<String, FormDefinition> getFormDefinitions() {
        return formDefinitions;
    }

    public static FormDefinition getFormDefinition(String formBean) {
        return formDefinitions.get(formBean);
    }

}
