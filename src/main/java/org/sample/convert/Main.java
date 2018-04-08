package org.sample.convert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Map;

import org.sample.convert.form.DefaultFormConvertorFactory;
import org.sample.convert.form.FormConvertor;
import org.sample.convert.form.FormConvertorFactory;
import org.sample.definition.DefinitionValues;
import org.sample.definition.FormDefinition;
import org.sample.util.ConvertProperties;
import org.sample.util.ResourceUtil;
import org.sample.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.debug("処理を開始します。");
        try {
            validateSetting();
            FormConvertorFactory formConvertorFactory = new DefaultFormConvertorFactory();
            String packageName = ConvertProperties.get("org.sample.convert.validator.package");
            String outDirPath = ConvertProperties.get("org.sample.convert.output");
            File outputDir = new File(outDirPath + "/" + packageName.replaceAll("\\.", "/"));
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            Charset encoding = Charset.forName(ConvertProperties.get("org.sample.convert.output.encoding"));
            for (Map.Entry<String, FormDefinition> entry : DefinitionValues.getFormDefinitions().entrySet()) {
                FormDefinition formDefinition = entry.getValue();
                FormConvertor formConvertor = formConvertorFactory.create(formDefinition);
                String converted = formConvertor.convert(formDefinition);
                File output = new File(outputDir, formDefinition.getFormBeanName() + "Validator.java");
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(output), encoding))) {
                    writer.write(converted);
                }
            }
        } catch (Throwable t) {
            LOGGER.error("エラーが発生しました。", t);
            System.exit(1);
        }
        LOGGER.debug("処理を終了します。");
    }

    /**
     * 設定ファイルの精査処理を行う。
     */
    private static void validateSetting() {
        // 出力ディレクトリの存在チェック
        String outputDir = ConvertProperties.get("org.sample.convert.output");
        if (StringUtils.isBlankOrNull(outputDir)) {
            throw new IllegalStateException("プロパティファイルにorg.sample.convert.outputが存在しません。");
        }
        File output = new File(outputDir);
        if (output.isFile()) {
            throw new IllegalStateException("出力ディレクトリにファイルが指定されています。outputDir=" + outputDir);
        }
        // パッケージ名
        if (StringUtils.isBlankOrNull(ConvertProperties.get("org.sample.convert.validator.package"))) {
            throw new IllegalStateException("プロパティファイルにorg.sample.convert.validator.packageが存在しません。");
        }
        // エンコード
        Charset.forName(ConvertProperties.get("org.sample.convert.output.encoding"));
        // struts-config.xml
        String strutsConfig = ConvertProperties.get("org.sample.convert.strut.config");
        if (StringUtils.isBlankOrNull(strutsConfig)) {
            throw new IllegalStateException("プロパティファイルにorg.sample.convert.strut.configが存在しません。");
        }
        try {
            ResourceUtil.getInputStream(strutsConfig);
        } catch (Exception e) {
            throw new RuntimeException("struts-config.xmlを読み込めません。struts-config.xml=" + strutsConfig, e);
        }
        // validation.xml
        String validationXml = ConvertProperties.get("org.sample.convert.strut.validation");
        if (StringUtils.isBlankOrNull(validationXml)) {
            throw new IllegalStateException("プロパティファイルにorg.sample.convert.strut.validationが存在しません。");
        }
        try {
            ResourceUtil.getInputStream(validationXml);
        } catch (Exception e) {
            throw new RuntimeException("validation.xmlを読み込めません。validation.xml=" + validationXml, e);
        }
        // validation-rule.xml
        String validationRule = ConvertProperties.get("org.sample.convert.strut.validation.rule");
        if (StringUtils.isBlankOrNull(validationRule)) {
            throw new IllegalStateException("プロパティファイルにorg.sample.convert.strut.validation.ruleが存在しません。");
        }
        for (String rule : validationRule.split(",")) {
            try {
                ResourceUtil.getInputStream(rule);
            } catch (Exception e) {
                throw new RuntimeException("validation-rule.xmlを読み込めません。validation-rule.xml=" + rule, e);
            }
        }
        // ClassFormat.txt
        String classFormat = ConvertProperties.get("org.sample.convert.format.class");
        if (StringUtils.isBlankOrNull(classFormat)) {
            throw new IllegalStateException("プロパティファイルにorg.sample.convert.format.classが存在しません。");
        }
        try {
            ResourceUtil.getInputStream(classFormat);
        } catch (Exception e) {
            throw new RuntimeException("ClassFormat.txtを読み込めません。ClassFormat.txt=" + classFormat, e);
        }
        // ValidateMethodFormat.txt
        String validateMethodFormat = ConvertProperties.get("org.sample.convert.format.private.method");
        if (StringUtils.isBlankOrNull(validateMethodFormat)) {
            throw new IllegalStateException("プロパティファイルにorg.sample.convert.format.private.methodが存在しません。");
        }
        try {
            ResourceUtil.getInputStream(validateMethodFormat);
        } catch (Exception e) {
            throw new RuntimeException("ValidateMethodFormat.txtを読み込めません。ValidateMethodFormat.txt=" + validateMethodFormat, e);
        }
        // PrivateMethodFormat.txt
        String privateMethodFormat = ConvertProperties.get("org.sample.convert.format.private.method");
        try {
            ResourceUtil.getInputStream(privateMethodFormat);
        } catch (Exception e) {
            throw new RuntimeException("PrivateMethodFormat.txtを読み込めません。PrivateMethodFormat.txt=" + privateMethodFormat, e);
        }
    }
}
