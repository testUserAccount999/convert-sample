package org.sample.struts.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;
import org.sample.struts.validation.Arg0;
import org.sample.struts.validation.Arg1;
import org.sample.struts.validation.Arg2;
import org.sample.struts.validation.Field;
import org.sample.struts.validation.Form;
import org.sample.struts.validation.FormValidation;
import org.sample.struts.validation.Formset;
import org.sample.struts.validation.Msg;
import org.sample.struts.validation.Var;
import org.sample.util.ResourceUtil;

public class VlidatetionXmlWriteTest {
    private static final String EMPTY = "";
    private static final String TRUE = "true";

    @Test
    public void writeValidationDefnition() throws Exception {
        final String xml = "org/sample/struts/validation/validation.xml";
        final String xlsx = "target/validation.xml.xlsx";
        FormValidation formValidation = null;
        try (InputStream inputStream = ResourceUtil.getInputStream(xml)) {
            formValidation = StrutsXmlParser.parseValidation(inputStream);
        }
        SXSSFWorkbook wb = new SXSSFWorkbook(1000);
        Sheet fieldSheet = wb.createSheet("field");
        writeFieldSheetHeader(fieldSheet);
        Sheet fieldVarSheet = wb.createSheet("fieldVar");
        writeFieldVarSheet(fieldVarSheet);
        int fieldSheetRowIndex = 1;
        int fieldVarSheetRowIndex = 1;
        for (Formset formSet : formValidation.getFormset()) {
            for (Form form : formSet.getForm()) {
                String formName = form.getName();
                for (Field field : form.getField()) {
                    String property = field.getProperty();
                    Row fieldSheetRow = fieldSheet.createRow(fieldSheetRowIndex++);
                    writeField(formName, fieldSheetRow, field);
                    List<Var> varList = field.getVar();
                    writeVar(varList, fieldVarSheet, fieldVarSheetRowIndex, formName, property);
                    fieldVarSheetRowIndex += varList.size();
                }
            }
        }
        try (FileOutputStream out = new FileOutputStream(xlsx)) {
            wb.write(out);
        }
        wb.close();
        wb.dispose();
    }

    private void writeVar(List<Var> varList, Sheet fieldVarSheet, int fieldVarSheetRowIndex, String formName, String property) {
        for (Var var : varList) {
            Row row = fieldVarSheet.createRow(fieldVarSheetRowIndex++);
            int columnIndex = 0;
            row.createCell(columnIndex++).setCellValue(formName);
            row.createCell(columnIndex++).setCellValue(property);
            row.createCell(columnIndex++).setCellValue(var.getVarName());
            row.createCell(columnIndex++).setCellValue(var.getVarValue());
        }
    }

    private void writeFieldVarSheet(Sheet fieldVarSheet) {
        Row row = fieldVarSheet.createRow(0);
        int columnIndex = 0;
        row.createCell(columnIndex++).setCellValue("form-name");
        row.createCell(columnIndex++).setCellValue("property");
        row.createCell(columnIndex++).setCellValue("var-name");
        row.createCell(columnIndex++).setCellValue("var-value");
    }

    private void writeField(String formName, Row row, Field field) {
        int columnIndex = 0;
        row.createCell(columnIndex++).setCellValue(formName);
        row.createCell(columnIndex++).setCellValue(field.getProperty());
        row.createCell(columnIndex++).setCellValue(field.getDepends() != null ? field.getDepends().replaceAll(" ", EMPTY) : EMPTY);
        // Arg0
        List<Arg0> arg0List = field.getArg0();
        if (arg0List.isEmpty()) {
            columnIndex += 3;
        } else {
            Arg0 arg0 = arg0List.get(0);
            row.createCell(columnIndex++).setCellValue(arg0.getName() != null ? arg0.getName() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg0.getKey() != null ? arg0.getKey() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg0.getResource() != null ? arg0.getResource() : TRUE);
        }
        // Arg1
        List<Arg1> arg1List = field.getArg1();
        if (arg1List.size() > 0) {
            Arg1 arg1 = arg1List.get(0);
            row.createCell(columnIndex++).setCellValue(arg1.getName() != null ? arg1.getName() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg1.getKey() != null ? arg1.getKey() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg1.getResource() != null ? arg1.getResource() : TRUE);
        } else {
            columnIndex += 3;
        }
        if (arg1List.size() > 1) {
            Arg1 arg1 = arg1List.get(1);
            row.createCell(columnIndex++).setCellValue(arg1.getName() != null ? arg1.getName() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg1.getKey() != null ? arg1.getKey() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg1.getResource() != null ? arg1.getResource() : TRUE);
        } else {
            columnIndex += 3;
        }
        if (arg1List.size() > 2) {
            Arg1 arg1 = arg1List.get(2);
            row.createCell(columnIndex++).setCellValue(arg1.getName() != null ? arg1.getName() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg1.getKey() != null ? arg1.getKey() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg1.getResource() != null ? arg1.getResource() : TRUE);
        } else {
            columnIndex += 3;
        }
        // Arg2
        List<Arg2> arg2List = field.getArg2();
        if (arg2List.isEmpty()) {
            columnIndex += 3;
        } else {
            Arg2 arg2 = arg2List.get(0);
            row.createCell(columnIndex++).setCellValue(arg2.getName() != null ? arg2.getName() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg2.getKey() != null ? arg2.getKey() : EMPTY);
            row.createCell(columnIndex++).setCellValue(arg2.getResource() != null ? arg2.getResource() : TRUE);
        }
        // Msg
        Msg msg = field.getMsg();
        if (msg == null) {
            columnIndex += 3;
        } else {
            row.createCell(columnIndex++).setCellValue(msg.getName() != null ? msg.getName() : EMPTY);
            row.createCell(columnIndex++).setCellValue(msg.getKey() != null ? msg.getKey() : EMPTY);
            row.createCell(columnIndex++).setCellValue(msg.getResource() != null ? msg.getResource() : TRUE);
        }
    }

    private void writeFieldSheetHeader(Sheet sh) {
        Row row = sh.createRow(0);
        int columnIndex = 0;
        row.createCell(columnIndex++).setCellValue("form-name");
        row.createCell(columnIndex++).setCellValue("property");
        row.createCell(columnIndex++).setCellValue("depends");
        row.createCell(columnIndex++).setCellValue("arg0-name");
        row.createCell(columnIndex++).setCellValue("arg0-key");
        row.createCell(columnIndex++).setCellValue("arg0-resource");
        row.createCell(columnIndex++).setCellValue("arg1.1-name");
        row.createCell(columnIndex++).setCellValue("arg1.1-key");
        row.createCell(columnIndex++).setCellValue("arg1.1-resource");
        row.createCell(columnIndex++).setCellValue("arg1.2-name");
        row.createCell(columnIndex++).setCellValue("arg1.2-key");
        row.createCell(columnIndex++).setCellValue("arg1.2-resource");
        row.createCell(columnIndex++).setCellValue("arg1.3-name");
        row.createCell(columnIndex++).setCellValue("arg1.3-key");
        row.createCell(columnIndex++).setCellValue("arg1.3-resource");
        row.createCell(columnIndex++).setCellValue("arg2-name");
        row.createCell(columnIndex++).setCellValue("arg2-key");
        row.createCell(columnIndex++).setCellValue("arg2-resource");
        row.createCell(columnIndex++).setCellValue("msg-name");
        row.createCell(columnIndex++).setCellValue("msg-key");
        row.createCell(columnIndex++).setCellValue("msg-resource");
    }

}
