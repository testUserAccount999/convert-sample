package org.sample.definition;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Test;

public class DefinitionReaderTest {

    @Test
    public void testDefinitionReader() throws Exception {
        String strutsConfig = "org/sample/struts/config/struts-config.xml";
        String[] validationXmls = {"org/sample/struts/validation/validation.xml"};
        String[] validationRules = {"org/sample/struts/rule/validator-rules.xml"};
        DefinitionReader reader = new DefinitionReader(strutsConfig, validationXmls, validationRules);
        assertThat(reader , notNullValue());
        Map<String, ValidatorDefinition> validatorDefinitions = reader.readValidatorDefinition();
        assertFalse(validatorDefinitions.isEmpty());
        Map<String, FormDefinition> formDefinition = reader.readFormDefinition();
        assertFalse(formDefinition.isEmpty());
    }

}
