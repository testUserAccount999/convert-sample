package org.sample;

import java.util.Map;

import org.junit.Test;
import org.sample.convert.form.SimpleFormConvertor;
import org.sample.definition.DefinitionValues;
import org.sample.definition.FormDefinition;

public class AppTest {

    @Test
    public void testExecute() throws Exception {
        for(Map.Entry<String, FormDefinition> entry: DefinitionValues.getFormDefinitions().entrySet()) {
            System.out.println( new SimpleFormConvertor().convert(entry.getValue()));
        }

    }
}
