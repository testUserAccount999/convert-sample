package org.sample.definition;

import static org.junit.Assert.*;

import org.junit.Test;

public class DefinitionValuesTest {
    @Test
    public void testStaticInitialize() throws Exception {
        assertFalse(DefinitionValues.getFormDefinitions().isEmpty());
        assertFalse(DefinitionValues.getValidatorDefinitions().isEmpty());
    }
}
