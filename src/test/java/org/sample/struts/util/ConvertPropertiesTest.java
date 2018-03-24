package org.sample.struts.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.sample.util.ConvertProperties;

public class ConvertPropertiesTest {

    @Test
    public void testStaticInitialize() throws Exception {
        assertThat(ConvertProperties.get("org.sample.convert.output"), notNullValue());
    }

}
