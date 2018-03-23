package org.sample.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormDefinition {
    private static final String EMPTY = "";
    private static final String ERASE_PACKAGE_REGEX = ".+\\.";
    private String formBeanName;
    private String formClassName;
    private String formSimpleClassName;
    private Map<String, List<FieldDefinition>> fieldDefinitions = new HashMap<>();

    public FormDefinition(String formBeanName, String formClassName) {
        this.formBeanName = formBeanName;
        this.formClassName = formClassName;
        formSimpleClassName = formClassName.replaceAll(ERASE_PACKAGE_REGEX, EMPTY);
    }

    public String getFormBeanName() {
        return formBeanName;
    }

    public String getFormClassName() {
        return formClassName;
    }

    public String getFormSimpleClassName() {
        return formSimpleClassName;
    }

    public void addFieldDefinition(FieldDefinition fieldDefinition) {
        if(!fieldDefinitions.containsKey(fieldDefinition.getProperty())) {
            fieldDefinitions.put(fieldDefinition.getProperty(), new ArrayList<>());
        }
        fieldDefinitions.get(fieldDefinition.getProperty()).add(fieldDefinition);
    }

    public Map<String, List<FieldDefinition>> getFieldDefinitions() {
        return fieldDefinitions;
    }

    public void addFieldDefitnion(FieldDefinition FieldDefinition) {
    }

}
