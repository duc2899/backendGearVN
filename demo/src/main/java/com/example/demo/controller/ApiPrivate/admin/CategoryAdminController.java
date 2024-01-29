package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.service.CategoryServices.CategoryServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private/admin/category")
@CrossOrigin(origins = "**")
public class CategoryAdminController {
    private final CategoryServices categoryServices;

    public CategoryAdminController(CategoryServices categoryServices) {
        this.categoryServices = categoryServices;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategory() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, categoryServices.getAllCategory());
    }
}
