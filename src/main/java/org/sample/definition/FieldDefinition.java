package org.sample.definition;

import java.util.HashMap;
import java.util.Map;

import org.sample.struts.validation.Field;
import org.sample.struts.validation.Var;
import org.sample.util.DefinitionUtil;

public class FieldDefinition {
    private static final String COMMA = ",";
    private static final String EMPTY = "";
    private static final String SPACE = " ";
    private String property;
    private String dependsType;
    private String[] depends;
    private Map<String, String> varMap = new HashMap<>();
    private Map<String, ArgDefinition[]> argDefinitions;
    private Field field;
    private IfCondition ifCondition;
    private boolean containsIf = false;
    private String definitionOrder;

    public FieldDefinition(Field field, String definitionOrder) {
        this(field);
        this.definitionOrder = definitionOrder;
    }

    private FieldDefinition(Field field) {
        this.field = field;
        property = field.getProperty();
        depends = (field.getDepends() != null ? field.getDepends().replaceAll(SPACE, EMPTY).split(COMMA) : new String[0]);
        dependsType = String.join(COMMA, depends);
        for (Var var : field.getVar()) {
            varMap.put(var.getVarName(), var.getVarValue());
        }
        for (String depend : depends) {
            if (depend.endsWith("if")) {
                containsIf = true;
                break;
            }
        }
        ifCondition = DefinitionUtil.createIfCondition(varMap);
        argDefinitions = DefinitionUtil.resolveArgDefinition(depends, field, varMap);
    }

    public String getProperty() {
        return property;
    }

    public String[] getDepends() {
        return depends;
    }

    public String getDependsType() {
        return dependsType;
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

    public IfCondition getIfCondition() {
        return ifCondition;
    }

    public boolean isValidIfCondition() {
        if (containsIf) {
            return !ifCondition.getFieldConditions().isEmpty();
        } else {
            return true;
        }
    }

    public ArgDefinition[] getArgDefinition(String depend) {
        return argDefinitions.get(depend);
    }

    public String getDefinitionOrder() {
        return definitionOrder;
    }
}
