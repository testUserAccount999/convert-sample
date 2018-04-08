package org.sample.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sample.convert.FormatValues;
import org.sample.convert.validation.ConvertValue;
import org.sample.definition.ArgDefinition;
import org.sample.definition.DefinitionValues;
import org.sample.definition.FieldCondition;
import org.sample.definition.FieldDefinition;
import org.sample.definition.IfCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormatUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FormatUtil.class);
    private static final String VAR_REGEX = "\\$\\{var:([a-zA-z\\d]+)\\}";
    private static final Pattern VAR_PATTERN = Pattern.compile(VAR_REGEX);
    private static final String MESSAGE_SOURCE_FORMAT = "messageSource.getMessage(\"${argKey}\", null, Locale.JAPAN)";

    private FormatUtil() {
    }

    public static final StringBuilder mergeBranchLogic(String previous, String now) {
        List<String> previousList = new ArrayList<>();
        List<String> nowList = new ArrayList<>();
        try (BufferedReader prefiousReader = new BufferedReader(new StringReader(previous));
                BufferedReader nextReader = new BufferedReader(new StringReader(now))) {
            String line = null;
            while ((line = prefiousReader.readLine()) != null) {
                previousList.add(line);
            }
            line = null;
            while ((line = nextReader.readLine()) != null) {
                nowList.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("", e);
        }
        StringBuilder sb = new StringBuilder();
        // 最終行のカッコを除去
        for (int i = 0; i < previousList.size() - 1; i++) {
            sb.append(previousList.get(i)).append(System.lineSeparator());
        }
        // 最初の行の分岐ロジックを除去
        for (int i = 1; i < nowList.size(); i++) {
            sb.append(nowList.get(i)).append(System.lineSeparator());
        }
        return sb;
    }

    /**
     * validation名、フィールド定義をもとに変換結果のargsとmsgを上書きを行う。<br />
     * @param validatorName validation名
     * @param fieldDefinition フィールド定義
     * @param convertValue 変換結果
     * @return 上書きされた変換結果
     */
    public static final ConvertValue overrideArgsAndMsg(String validatorName, FieldDefinition fieldDefinition, ConvertValue convertValue) {
        // msg上書き
        String msg = DefinitionValues.getValidatorDefinition(validatorName).getValidator().getMsg();
        if (fieldDefinition.getField().getMsg() != null && validatorName.equals(fieldDefinition.getField().getMsg().getName())) {
            msg = fieldDefinition.getField().getMsg().getKey();
        }
        if (!StringUtils.isBlankOrNull(msg)) {
            convertValue.getKeyValue().put("msg", msg);
        }
        // arg設定を上書き
        convertValue.getKeyValue().put("args",
                FormatUtil.createArgs(validatorName, fieldDefinition.getArgDefinition(validatorName), fieldDefinition.getVarMap()));
        return convertValue;
    }

    /**
     * 変換されたValidatorに記述するインポート文を返す。<br />
     * 対象となるクラスは<code>org.sample.convert.import.classes</code>に定義されている必要がある。
     * @param validatorString 変換されたValidator
     * @return インポート文
     */
    public static final String resolveImportClass(String validatorString) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : FormatValues.getImportClasses().entrySet()) {
            String key = entry.getKey() + ".";
            if (validatorString.contains(key)) {
                sb.append("import ").append(entry.getValue()).append(";").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    /**
     * 精査条件を適用するかどうかの分岐条件を作成する。<br />
     * @param validatorName validator名
     * @param ifCondition 分岐条件定義
     * @return 分岐条件文(ifは含まない)
     */
    public static final String createCondition(String validatorName, IfCondition ifCondition) {
        if (ifCondition.getFieldConditions().isEmpty()) {
            throw new IllegalArgumentException("条件が設定されていません。");
        }

        String join = " && ";
        if ("OR".equals(ifCondition.getFieldJoin())) {
            join = " || ";
        }
        StringBuilder returnSb = new StringBuilder();
        for (int i = 0; i < ifCondition.getFieldConditions().size(); i++) {
            FieldCondition condition = ifCondition.getFieldConditions().get(i);
            StringBuilder sb = new StringBuilder();
            String propertyUpperCamelCase = toUpperCamelProperty(condition.getFieldName());
            String test = condition.getFieldTest();
            if ("EQUAL".equalsIgnoreCase(test) || "NOTEQUAL".equalsIgnoreCase(test)) {
                String testValue = condition.getFieldValue();
                sb.append('"').append(testValue).append('"').append(".equalsIgnoreCase(");
                sb.append("${org.sample.convert.util.").append(validatorName).append("}.toStringValue(form.get").append(propertyUpperCamelCase);
                sb.append("()))");
            } else if ("NULL".equalsIgnoreCase(test) || "NOTNULL".equalsIgnoreCase(test)) {
                sb.append("${org.sample.convert.util.").append(validatorName).append("}.isBlankOrNull(form.get");
                sb.append(propertyUpperCamelCase);
                sb.append("())");
            } else {
                throw new IllegalArgumentException("想定しない条件が設定されています。fieldTest=" + test);
            }
            if ("NOTEQUAL".equalsIgnoreCase(test) || "NOTNULL".equalsIgnoreCase(test)) {
                sb.insert(0, '!');
            }
            if (i != ifCondition.getFieldConditions().size() - 1) {
                sb.append(join);
            }
            returnSb.append(sb);
        }
        return returnSb.toString();
    }

    /**
     * エラーメッセージの引数を作成する。<br />
     * <code>org.springframework.validation.Errors#rejectValue(String, String, Object[], String)</code>の第3引数となる文字列を返却する。
     * @param validatorName validator名
     * @param argDefinitions 引数定義
     * @param varMap var定義
     * @return エラーメッセージの引数
     */
    public static final String createArgs(String validatorName, ArgDefinition[] argDefinitions, Map<String, String> varMap) {
        if (argDefinitions == null || argDefinitions.length == 0) {
            // argなしは文字列：nullを返却
            return "null";
        }
        List<String> argList = new ArrayList<String>();
        for (int i = 0; i < argDefinitions.length; i++) {
            ArgDefinition argDefinition = argDefinitions[i];
            if (argDefinition == null) {
                // nullになることはないはずなのでエラーをスロー
                continue;
            }
            boolean isTarget = false;
            // nullならすべてに適用
            if (null == argDefinition.getArgName()) {
                // nullならすべてに適用
                isTarget = true;
            } else if (validatorName.equals(argDefinition.getArgName())) {
                // arg nameが一致した場合は適用
                isTarget = true;
            }
            if (isTarget) {
                String arg = null;
                if (argDefinition.isResource()) {
                    Map<String, String> keyValue = new HashMap<>();
                    keyValue.put("argKey", argDefinition.getArgKey());
                    arg = format(MESSAGE_SOURCE_FORMAT, keyValue);
                } else {
                    // varへの参照を解決したうえでキーをダブルクォーテーションで囲んで格納。
                    arg = "\"" + resolveArgKey(argDefinition, varMap) + "\"";
                }
                argList.add(arg);
            }
        }
        String args = String.join(", ", argList);
        if ("".equals(args)) {
            args = "null";
        } else {
            args = "new Object[]{" + args + "}";
        }
        return args;
    }

    private static String resolveArgKey(ArgDefinition argDefinition, Map<String, String> varMap) {
        Matcher matcher = VAR_PATTERN.matcher(argDefinition.getArgKey());
        int index = 0;
        List<String> varList = new ArrayList<>();
        while (matcher.find(index)) {
            varList.add(matcher.group(1));
            index = matcher.end();
        }
        Map<String, String> keyValue = new HashMap<>();
        for (String varName : varList) {
            keyValue.put("var:" + varName, varMap.get(varName));
        }
        return format(argDefinition.getArgKey(), keyValue);
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
