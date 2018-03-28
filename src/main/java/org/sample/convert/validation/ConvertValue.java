package org.sample.convert.validation;

import java.util.Map;

import org.sample.util.FormatUtil;

public class ConvertValue {

    private Map<String, String> keyValue;
    private String format;

    public ConvertValue(String format, Map<String, String> keyValue) {
        this.format = format;
        this.keyValue = keyValue;
    }

    public Map<String, String> getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(Map<String, String> keyValue) {
        this.keyValue = keyValue;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String toString() {
        return FormatUtil.format(format, keyValue);
    }
}
