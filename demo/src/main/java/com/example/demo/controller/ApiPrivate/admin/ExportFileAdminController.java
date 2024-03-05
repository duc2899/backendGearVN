package com.example.demo.controller.ApiPrivate.admin;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private/admin/export")
@CrossOrigin(origins = "http://localhost:4000")
public class ExportFileAdminController {

//    @GetMapping("/users")
//    public ResponseEntity<Object> exportExcelUser (HttpServletResponse httpServletResponse) {
//        httpServletResponse.setContentType("application/octet-stream");
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachement; filename=users.xlsx";
//
//        httpServletResponse.setHeader(headerKey, headerValue);
//    }
}

