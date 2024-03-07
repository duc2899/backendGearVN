package com.example.demo.service.ExportFilesServices;

import com.example.demo.modal.UserModalPackage.UserModal;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
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
        CellStyle style = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeight(16);
        font.setBold(true);

        style.setFont(font);

        Cell cell = row.createCell(0);
        cell.setCellValue("User ID");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Created Date");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Name");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("Email");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("Phone Number");
        cell.setCellStyle(style);

        cell = row.createCell(5);
        cell.setCellValue("isActivate");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("Role");
        cell.setCellStyle(style);
    }

    private void writeDataRows() {
        int rowCount = 1;
        CellStyle style = xssfWorkbook.createCellStyle();
        XSSFFont font = xssfWorkbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (UserModal userModal : modalList) {
            Row row = sheet.createRow(rowCount++);

            Cell cell = row.createCell(0);
            cell.setCellValue(userModal.getId_user());
            sheet.autoSizeColumn(0);
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(userModal.getCreatedDate());
            sheet.autoSizeColumn(1);
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(userModal.getName());
            sheet.autoSizeColumn(2);
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(userModal.getEmail());
            sheet.autoSizeColumn(3);
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(userModal.getPhoneNumber());
            sheet.autoSizeColumn(4);
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(userModal.getIsActive() ? "Activate" : "InActivate");
            sheet.autoSizeColumn(5);
            cell.setCellStyle(style);

            cell = row.createCell(6);
            cell.setCellValue(userModal.getRole().toString());
            sheet.autoSizeColumn(6);
            cell.setCellStyle(style);
        }

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        writeDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        xssfWorkbook.write(outputStream);
        xssfWorkbook.close();
        outputStream.close();

    }


}
