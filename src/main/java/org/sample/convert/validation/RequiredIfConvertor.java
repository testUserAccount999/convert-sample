package org.sample.convert.validation;

import java.util.HashMap;
import java.util.Map;

import org.sample.definition.DefinitionValues;
import org.sample.definition.FieldDefinition;
import org.sample.util.FormatUtil;

public class RequiredIfConvertor implements ValidationConvertor {

	ValidationConvertorFactory factory = new DefaultValidationConvertorFactory();

	private static final String FORMAT_PREFIX = "\t\tif (${condition}) {" + System.lineSeparator();
	private static final String FORMAT_SUFFIX = "\t\t}" + System.lineSeparator();
	private String validatorName;

	public RequiredIfConvertor(String validatorName) {
		this.validatorName = validatorName;
	}

	@Override
	public ConvertValue convert(FieldDefinition fieldDefinition) {
		if (!fieldDefinition.isValidIfCondition()) {
			return new ConvertValue();
		}

		int index = validatorName.lastIndexOf("if");
		ConvertValue convertValue = factory.create(validatorName.substring(0, index)).convert(fieldDefinition);
		String msg = DefinitionValues.getValidatorDefinition(validatorName).getValidator().getMsg();
		if (fieldDefinition.getField().getMsg() != null
				&& validatorName.equals(fieldDefinition.getField().getMsg().getName())) {
			msg = fieldDefinition.getField().getMsg().getKey();
		}
		convertValue.putKeyValue("msg", msg);
		String requiredPart = convertValue.toString();
		if ("".equals(requiredPart)) {
			return new ConvertValue();
		}
		requiredPart = FormatUtil.addIndent(requiredPart);
		String condition = FormatUtil.createCondition(fieldDefinition.getIfCondition());
		Map<String, String> keyValue = new HashMap<>();
		keyValue.put("condition", condition);
		String prefix = FormatUtil.format(FORMAT_PREFIX, keyValue);
		// return prefix + requiredPart + FORMAT_SUFFIX;
		return new ConvertValue(prefix + requiredPart + FORMAT_SUFFIX, keyValue);
	}

}
