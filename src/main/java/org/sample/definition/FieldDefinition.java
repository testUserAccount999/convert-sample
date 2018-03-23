package org.sample.definition;

import java.util.HashMap;
import java.util.Map;

import org.sample.struts.validation.Field;
import org.sample.struts.validation.Var;

public class FieldDefinition {
    private static final String COMMA = ",";
    private static final String EMPTY = "";
    private static final String SPACE = " ";
    private String property;
    private String[] depends;
    private Map<String, String> varMap = new HashMap<>();
    private Field field;

    public FieldDefinition(Field field) {
        this.field = field;
        property = field.getProperty();
        depends = field.getDepends() != null ? field.getDepends().replaceAll(SPACE, EMPTY).split(COMMA) : new String[0];
        for (Var var : field.getVar()) {
            varMap.put(var.getVarName(), var.getVarValue());
        }
    }

    public String getProperty() {
        return property;
    }

    public String[] getDepends() {
        return depends;
    }

    public Map<String, String> getVarMap() {
        return varMap;
    }

    public String getVarValue(String varName) {
        return varMap.get(varName);
    }

    public Field getField() {
        return field;
    }

}
