package org.sample.definition;

public class FieldCondition {
    private String fieldName;
    private String fieldTest;
    private String fieldValue;

    public FieldCondition(String fieldName, String fieldTest, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldTest = fieldTest;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldTest() {
        return fieldTest;
    }

    public String getFieldValue() {
        return fieldValue;
    }

}
