package org.sample.struts.rule;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.InputStream;

import org.junit.Test;
import org.sample.struts.util.StrutsXmlParser;
import org.sample.util.ResourceUtil;


public class ValidationRuleReadTest {

    @Test
    public void readTest() throws java.lang.Exception {
        String xml = "org/sample/struts/rule/validator-rules.xml";
        try (InputStream inputStream = ResourceUtil.getInputStream(xml)) {
            FormValidation formValidation = StrutsXmlParser.parseValidationRule(inputStream);
            for (Global global : formValidation.getGlobal()) {
                for (Validator validator : global.getValidator()) {
                    assertThat(validator.getName(), notNullValue());
                    assertThat(validator.getClass(), notNullValue());
                    assertThat(validator.getMethod(), notNullValue());
                    assertThat(validator.getMsg(), notNullValue());
                }
            }
        }
    }

}
