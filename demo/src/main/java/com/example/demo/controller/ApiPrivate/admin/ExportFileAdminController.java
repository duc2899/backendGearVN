package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.service.AccountUserServices.AccountUserServices;
import com.example.demo.service.ExportFilesServices.UserExcelExporter;
import com.example.demo.utilities.ResponseHandel;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("api/private/admin/export")
@CrossOrigin(origins = "http://localhost:4000")
public class ExportFileAdminController {

    private final AccountUserServices accountUserServices;

    public ExportFileAdminController(AccountUserServices accountUserServices) {
        this.accountUserServices = accountUserServices;
    }

    @GetMapping("/users")
    public ResponseEntity<Object> exportExcelUser(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormat.format(new Date());
        String fileName = "users_" + currentDateTime + ".xlsx";
        String headerValue = "attachement; filename=" + fileName;

        httpServletResponse.setHeader(headerKey, headerValue);
        UserExcelExporter userExcelExporter = new UserExcelExporter(accountUserServices.findAllUser());
        userExcelExporter.export(httpServletResponse);
        return ResponseHandel.generateResponse("success", HttpStatus.OK, fileName);
    }
}

