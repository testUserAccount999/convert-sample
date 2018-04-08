package org.sample.convert.form;

public class DefaultAdjustment implements Adjustment {
    @Override
    public String adjust(String formBeanName, String validatorString) {
        return validatorString;
    }

}
