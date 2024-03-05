package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.service.ChartServices.ChartService;
import com.example.demo.service.ProductServices.ProductServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private/admin/dashboard")
@CrossOrigin(origins = "http://localhost:4000")
public class ChartAdminController {

    private final ChartService chartService;
    private final ProductServices productServices;

    public ChartAdminController(ChartService chartService, ProductServices productServices) {
        this.chartService = chartService;
        this.productServices = productServices;
    }

    @GetMapping("/chart")
    public ResponseEntity<Object> getChartMonth() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, chartService.getRevenueMonth());
    }

    @GetMapping("/product")
    public ResponseEntity<Object> getProduct() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, productServices.getProductDashBoard());
    }
}
