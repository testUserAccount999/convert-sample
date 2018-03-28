package org.sample.definition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IfCondition {
    private String fieldJoin;// デフォルト値
    private List<FieldCondition> fieldConditions = new ArrayList<>();

    public IfCondition(String fieldJoin) {
        this.fieldJoin = fieldJoin;
    }

    public String getFieldJoin() {
        return fieldJoin;
    }

    public List<FieldCondition> getFieldConditions() {
        return fieldConditions;
    }

    public void addFieldCondition(FieldCondition fieldCondition) {
        fieldConditions.add(fieldCondition);
    }

    public String toConditionString() {
        List<String> list = new ArrayList<>();
        for (FieldCondition condition : fieldConditions) {
            String str = "name=" + condition.getFieldName() + ", test=" + condition.getFieldTest() + ", value=" + condition.getFieldValue();
            list.add(str);
        }
        Collections.sort(list);

        return list.toString();
    }
}
