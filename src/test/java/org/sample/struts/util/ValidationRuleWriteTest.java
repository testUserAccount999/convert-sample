package org.sample.struts.util;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.sample.struts.rule.FormValidation;
import org.sample.struts.rule.Global;
import org.sample.struts.rule.Validator;
import org.sample.util.ResourceUtil;

public class ValidationRuleWriteTest {

    @Test
    public void writeValidatorDefinition() throws Exception {
        final String xml = "org/sample/struts/rule/validator-rules.xml";
        final String xlsx = "target/validation-rule.xml.xlsx";
        FormValidation formValidation = null;
        try (InputStream inputStream = ResourceUtil.getInputStream(xml)) {
            formValidation = StrutsXmlParser.parseValidationRule(inputStream);
        }
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        Sheet sheet = wb.createSheet("validator");
        writeSheetHeader(sheet);
        int rowIndex = 1;
        for (Global global : formValidation.getGlobal()) {
            for (Validator validator : global.getValidator()) {
                Row row = sheet.createRow(rowIndex++);
                int columnIndex = 0;
                row.createCell(columnIndex++).setCellValue(validator.getName());
                row.createCell(columnIndex++).setCellValue(validator.getClassname());
                row.createCell(columnIndex++).setCellValue(validator.getMethod());
                row.createCell(columnIndex++).setCellValue(validator.getDepends());
                row.createCell(columnIndex++).setCellValue(validator.getMsg());
            }
        }
        try (FileOutputStream out = new FileOutputStream(xlsx)) {
            wb.write(out);
        }
        wb.close();
        wb.dispose();
    }

    private void writeSheetHeader(Sheet sheet) {
        Row row = sheet.createRow(0);
        int index = 0;
        row.createCell(index++).setCellValue("name");
        row.createCell(index++).setCellValue("class");
        row.createCell(index++).setCellValue("method");
        row.createCell(index++).setCellValue("depends");
        row.createCell(index++).setCellValue("msg");
    }

}
