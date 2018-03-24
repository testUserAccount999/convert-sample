package org.sample.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.sample.definition.ArgDefinition;
import org.sample.definition.FieldCondition;
import org.sample.definition.FieldDefinition;
import org.sample.definition.FormDefinition;
import org.sample.definition.IfCondition;
import org.sample.struts.validation.Arg0;
import org.sample.struts.validation.Arg1;
import org.sample.struts.validation.Arg2;
import org.sample.struts.validation.Arg3;
import org.sample.struts.validation.Field;
import org.sample.struts.validation.Var;

public final class DefinitionUtil {

    private DefinitionUtil() {
    }

    public static final Map<String, List<FieldDefinition>> groupCondition(FormDefinition formDefinition) {
        Map<String, List<FieldDefinition>> tempMap = new TreeMap<>();
        for (Map.Entry<String, List<FieldDefinition>> entry : formDefinition.getFieldDefinitions().entrySet()) {
            for (FieldDefinition fieldDefinition : entry.getValue()) {
                String condition = "[NULL]";
                if (fieldDefinition.getIfCondition() != null) {
                    condition = fieldDefinition.getIfCondition().getConditionString();
                }
                if (!tempMap.containsKey(condition)) {
                    tempMap.put(condition, new ArrayList<>());
                }
                tempMap.get(condition).add(fieldDefinition);
            }
        }
        Map<String, List<FieldDefinition>> map = new TreeMap<>();
        for (Map.Entry<String, List<FieldDefinition>> entry : tempMap.entrySet()) {
            List<FieldDefinition> definitons = entry.getValue();
            definitons = sortFieldDefinitonList(definitons);
            map.put(entry.getKey(), definitons);
        }
        return map;
    }

    public static final List<FieldDefinition> sortFieldDefinition(FormDefinition formDefinition) {
        List<FieldDefinition> sorted = new ArrayList<>();
        for (Map.Entry<String, List<FieldDefinition>> entry : formDefinition.getFieldDefinitions().entrySet()) {
            sorted.addAll(entry.getValue());
        }
        return sortFieldDefinitonList(sorted);
    }

    private static List<FieldDefinition> sortFieldDefinitonList(List<FieldDefinition> list) {
        List<FieldDefinition> sorted = new ArrayList<>(list);
        Collections.sort(sorted, new Comparator<FieldDefinition>() {
            @Override
            public int compare(FieldDefinition o1, FieldDefinition o2) {
                return o1.getDefinitionOrder().compareTo(o2.getDefinitionOrder());
            }
        });
        return sorted;
    }

    public static final boolean isSimpleDefiniton(FormDefinition formDefinition) {
        boolean isMultiFieldDefinition = false;
        // 定義が複数ある場合は、順序を考慮しないければならない。
        for (Map.Entry<String, List<FieldDefinition>> entry : formDefinition.getFieldDefinitions().entrySet()) {
            if (entry.getValue().size() != 1) {
                isMultiFieldDefinition = true;
                break;
            }
        }
        if (!isMultiFieldDefinition) {
            return true;
        }
//        boolean isIrregularOrder = false;
//        for (Map.Entry<String, List<FieldDefinition>> entry : formDefinition.getFieldDefinitions().entrySet()) {
//            if (entry.getValue().size() != 1) {
//                List<FieldDefinition> definitions = entry.getValue();
//                for (int i = 0; i < definitions.size() - 1; i++) {
//                    int order = Integer.valueOf(definitions.get(i).getDefinitionOrder());
//                    int nextOrder = Integer.valueOf(definitions.get(i + 1).getDefinitionOrder());
//                    if ((nextOrder - order) != 1) {
//                        isIrregularOrder = true;
//                        break;
//                    }
//                }
//                if (isIrregularOrder) {
//                    break;
//                }
//            }
//        }
//        if (!isIrregularOrder) {
//            return true;
//        }
        return false;
    }

    public static final Map<String, List<FieldDefinition>> groupFieldDefiniton(List<FieldDefinition> fieldDefinitionList) {
        Map<String, List<FieldDefinition>> map = new HashMap<>();
        for (FieldDefinition definition : fieldDefinitionList) {
            if (!map.containsKey(definition.getDependsType())) {
                map.put(definition.getDependsType(), new ArrayList<>());
            }
            map.get(definition.getDependsType()).add(definition);
        }
        return map;
    }

    public static final IfCondition createIfCondition(Map<String, String> varMap) {
        String fieldJoin = varMap.get("fieldJoin");
        if (StringUtils.isBlankOrNull(fieldJoin)) {
            fieldJoin = "AND";
        }
        int i = 0;
        IfCondition ifCondition = new IfCondition(fieldJoin);
        while (!StringUtils.isBlankOrNull(varMap.get("field[" + i + "]"))) {
            String fieldName = varMap.get("field[" + i + "]");
            String fieldTest = varMap.get("fieldTest[" + i + "]");
            String fieldValue = varMap.get("fieldValue[" + i + "]");
            if (fieldName != null) {
                FieldCondition fieldCondition = new FieldCondition(fieldName, fieldTest, fieldValue);
                ifCondition.addFieldCondition(fieldCondition);
            }
            i++;
        }
        return ifCondition;
    }

    public static final Map<String, ArgDefinition[]> resolveArgDefinition(String[] depends, Field field, Map<String, String> varMap) {
        Map<String, ArgDefinition[]> argDefinitions = new HashMap<>();
        for (String depend : depends) {
            ArgDefinition[] array = createArgDefinitonArray(depend, field, varMap);
            argDefinitions.put(depend, array);
        }
        return argDefinitions;
    }

    public static final String getVarValue(String varName, List<Var> varList) {
        String value = null;
        for (Var var : varList) {
            if (varName.equals(var.getVarName())) {
                value = var.getVarValue();
                break;
            }
        }
        return value;
    }

    private static ArgDefinition[] createArgDefinitonArray(String depend, Field field, Map<String, String> varMap) {
        ArgDefinition[] array = new ArgDefinition[4];
        Arg0 arg0 = null;
        // 名前あり
        for (Arg0 arg : field.getArg0()) {
            if (depend.equals(arg.getName())) {
                arg0 = arg;
            }
        }
        // 名前なし
        if (arg0 == null) {
            for (Arg0 arg : field.getArg0()) {
                if (StringUtils.isBlankOrNull(arg.getName())) {
                    arg0 = arg;
                }
            }
        }
        if (arg0 != null) {
            String argKey = arg0.getKey();
            for (Map.Entry<String, String> var : varMap.entrySet()) {
                argKey = argKey.replaceAll("\\$\\{var:" + var.getKey() + "\\}", var.getValue());
            }
            boolean resource = (StringUtils.isBlankOrNull(arg0.getResource()) ? true : Boolean.valueOf(arg0.getResource()));
            array[0] = new ArgDefinition(depend, argKey, resource);
        }
        Arg1 arg1 = null;
        // 名前あり
        for (Arg1 arg : field.getArg1()) {
            if (depend.equals(arg.getName())) {
                arg1 = arg;
            }
        }
        // 名前なし
        if (arg1 == null) {
            for (Arg1 arg : field.getArg1()) {
                if (StringUtils.isBlankOrNull(arg.getName())) {
                    arg1 = arg;
                }
            }
        }
        if (arg1 != null) {
            String argKey = arg1.getKey();
            for (Map.Entry<String, String> var : varMap.entrySet()) {
                argKey = argKey.replaceAll("\\$\\{var:" + var.getKey() + "\\}", var.getValue());
            }
            boolean resource = (StringUtils.isBlankOrNull(arg1.getResource()) ? true : Boolean.valueOf(arg1.getResource()));
            array[1] = new ArgDefinition(depend, argKey, resource);
        }
        Arg2 arg2 = null;
        // 名前あり
        for (Arg2 arg : field.getArg2()) {
            if (depend.equals(arg.getName())) {
                arg2 = arg;
            }
        }
        // 名前なし
        if (arg2 == null) {
            for (Arg2 arg : field.getArg2()) {
                if (StringUtils.isBlankOrNull(arg.getName())) {
                    arg2 = arg;
                }
            }
        }
        if (arg2 != null) {
            String argKey = arg2.getKey();
            for (Map.Entry<String, String> var : varMap.entrySet()) {
                argKey = argKey.replaceAll("\\$\\{var:" + var.getKey() + "\\}", var.getValue());
            }
            boolean resource = (StringUtils.isBlankOrNull(arg2.getResource()) ? true : Boolean.valueOf(arg2.getResource()));
            array[2] = new ArgDefinition(depend, argKey, resource);
        }
        Arg3 arg3 = null;
        // 名前あり
        for (Arg3 arg : field.getArg3()) {
            if (depend.equals(arg.getName())) {
                arg3 = arg;
            }
        }
        // 名前なし
        if (arg3 == null) {
            for (Arg3 arg : field.getArg3()) {
                if (StringUtils.isBlankOrNull(arg.getName())) {
                    arg3 = arg;
                }
            }
        }
        if (arg3 != null) {
            String argKey = arg3.getKey();
            for (Map.Entry<String, String> var : varMap.entrySet()) {
                argKey = argKey.replaceAll("\\$\\{var:" + var.getKey() + "\\}", var.getValue());
            }
            boolean resource = (StringUtils.isBlankOrNull(arg3.getResource()) ? true : Boolean.valueOf(arg3.getResource()));
            array[3] = new ArgDefinition(depend, argKey, resource);
        }
        return array;
    }

}
