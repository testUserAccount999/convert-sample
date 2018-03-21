package org.sample.struts.util;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.InputStream;

import org.junit.Test;

public class StrutsXmlParserTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNull() throws Exception {
        StrutsXmlParser.parseStrutsConfig(null);
    }

    @Test
    public void testArgument() throws Exception {
        String xml = "org/sample/struts/config/struts-config.xml";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(xml)) {
            assertThat(StrutsXmlParser.parseStrutsConfig(inputStream), notNullValue());
        }
        xml = "org/sample/struts/validation/validation.xml";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(xml)) {
            assertThat(StrutsXmlParser.parseValidation(inputStream), notNullValue());
        }
        xml = "org/sample/struts/rule/validator-rules.xml";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(xml)) {
            assertThat(StrutsXmlParser.parseValidationRule(inputStream), notNullValue());
        }
    }

}
