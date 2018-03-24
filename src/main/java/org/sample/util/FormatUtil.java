package org.sample.util;

import java.util.Map;

import org.sample.definition.ArgDefinition;

public class FormatUtil {
    private FormatUtil() {
    }

    public static final String createArgs(ArgDefinition[] argDefinitions) {
        // TODO errorArgsの作成は要検討
        String arg = "";
        for (int i = 0; i < argDefinitions.length; i++) {
            ArgDefinition argDefinition = argDefinitions[i];
            if (i == 0) {
                arg = arg + "\"";
            }
            arg = arg + argDefinition.getArgKey();
            if (i == 3) {
                arg = arg + "\"";
            } else {
                arg = arg + "\", \"";
            }
        }
        return arg;
    }

    public static String toUpperCamelProperty(String property) {
        return property.substring(0, 1) + property.substring(1);
    }

    public static final String format(String pattern, Map<String, String> keyValue) {
        String formatted = pattern;
        for (Map.Entry<String, String> entry : keyValue.entrySet()) {
            if (entry.getValue() != null) {
                String regex = "\\$\\{" + entry.getKey() + "\\}";
                formatted = formatted.replaceAll(regex, entry.getValue());
            }
        }
        return formatted;
    }

}
