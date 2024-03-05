package com.example.demo.service.ExportFilesServices;

import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

public class UserExcelExporter {
    private final XSSFWorkbook xssfWorkbook;
    private final XSSFSheet sheet;

    private final List<UserModal> modalList;

    public UserExcelExporter(List<UserModal> modalList) {
        this.modalList = modalList;
        xssfWorkbook = new XSSFWorkbook();
        sheet = xssfWorkbook.createSheet("users");

    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
    }

    private void writeDataRows() {

    }

    public void export(HttpServletResponse response) {

    }


}
