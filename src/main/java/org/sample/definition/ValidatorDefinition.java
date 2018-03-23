package org.sample.definition;

import org.sample.struts.rule.Validator;

public class ValidatorDefinition {
    private static final String COMMA = ",";
    private static final String EMPTY = "";
    private static final String SPACE = " ";

    private String validatorName;
    private String validatorClassName;
    private String method;
    private String[] depends;
    private Validator validator;

    public ValidatorDefinition(Validator validator) {
        this.validator = validator;
        validatorName = validator.getName();
        validatorClassName = validator.getClassname();
        depends = validator.getDepends() != null ? validator.getDepends().replaceAll(SPACE, EMPTY).split(COMMA) : new String[0];
    }

    public String getValidatorName() {
        return validatorName;
    }

    public String getValidatorClassName() {
        return validatorClassName;
    }

    public String getMethod() {
        return method;
    }

    public String[] getDepends() {
        return depends;
    }

    public Validator getValidator() {
        return validator;
    }

}
