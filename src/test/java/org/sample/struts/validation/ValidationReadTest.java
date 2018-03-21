package org.sample.struts.validation;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStream;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.sample.struts.util.StrutsXmlParser;
import org.xml.sax.SAXException;

public class ValidationReadTest {

    @Test
    public void testExecute() throws Exception {
        String xml = "org/sample/struts/validation/validation.xml";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(xml)) {
            readTest(inputStream);
        }
    }

    private void readTest(InputStream inputStream) throws ParserConfigurationException, SAXException, JAXBException {
        assertThat(inputStream, notNullValue());
        FormValidation formValidation = StrutsXmlParser.parseValidation(inputStream);
        for (Global global : formValidation.getGlobal()) {
            for (Constant constant : global.getConstant()) {
                assertThat(constant.getConstantName(), notNullValue());
                assertThat(constant.getConstantValue(), notNullValue());
            }
        }
        for (Formset formSet : formValidation.getFormset()) {
            formSet.getCountry();
            formSet.getLanguage();
            for (Constant constant : formSet.getConstant()) {
                assertThat(constant.getConstantName(), notNullValue());
                assertThat(constant.getConstantValue(), notNullValue());
            }
            for (Form form : formSet.getForm()) {
                assertThat(form.getName(), notNullValue());
                for (Field field : form.getField()) {
                    assertThat(field.getProperty(), notNullValue());
                    field.getDepends();
                    field.getPage();
                    field.getIndexedListProperty();
                    for (Arg0 arg0 : field.getArg0()) {
                        arg0.getKey();
                        arg0.getName();
                        arg0.getResource();
                    }
                    for (Arg1 arg1 : field.getArg1()) {
                        arg1.getKey();
                        arg1.getName();
                        arg1.getResource();
                    }
                    for (Arg2 arg2 : field.getArg2()) {
                        arg2.getKey();
                        arg2.getName();
                        arg2.getResource();
                    }
                    for (Arg3 arg3 : field.getArg3()) {
                        arg3.getKey();
                        arg3.getName();
                        arg3.getResource();
                    }
                    Msg msg = field.getMsg();
                    if (msg != null) {
                        assertThat(msg.getKey(), notNullValue());
                        assertThat(msg.getName(), notNullValue());
                        msg.getResource();
                    }
                    for (Var var : field.getVar()) {
                        assertThat(var.getVarName(), notNullValue());
                        assertThat(var.getVarValue(), notNullValue());
                    }
                }
            }
        }
    }

}
