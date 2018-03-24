package org.sample.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

import org.sample.definition.ArgDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormatUtil.class);

    private FormatUtil() {
    }

    public static final String createArgs(ArgDefinition[] argDefinitions) {
        // TODO errorArgsの作成は要検討
        String arg = "";
        if(argDefinitions == null) {
            return arg;
        }
        for (int i = 0; i < argDefinitions.length; i++) {
            ArgDefinition argDefinition = argDefinitions[i];
            if (argDefinition == null) {
                continue;
            }
            if (i == 0) {
                arg = arg + "\"";
            }
            arg = arg + argDefinition.getArgKey();
            if (i == 3 || argDefinitions[i + 1] == null) {
                arg = arg + "\"";
            } else {
                arg = arg + "\", \"";
            }
        }
        return arg;
    }

    public static String addIndent(String validateLogic) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new StringReader(validateLogic))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append("\t").append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static String toUpperCamelProperty(String property) {
        return property.substring(0, 1).toUpperCase() + property.substring(1);
    }

    public static final String format(String pattern, Map<String, String> keyValue) {
        if (StringUtils.isBlankOrNull(pattern)) {
            return "";
        }
        String formatted = pattern;
        for (Map.Entry<String, String> entry : keyValue.entrySet()) {
            if (entry.getValue() != null) {
                String regex = "\\$\\{" + entry.getKey() + "\\}";
                String repalce = entry.getValue();
                try {
                    formatted = formatted.replaceAll(regex, repalce);
                } catch (Exception e) {
                    LOGGER.debug("pattern=[" + pattern + "], formatted=[" + "], key=[" + regex + "], value=[" + repalce + "]");
                    throw e;
                }

            }
        }
        return formatted;
    }

}
