package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.service.ProductServices.LaptopProductServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/user/laptopProduct")
public class LaptopProductUserController {
    private final LaptopProductServices laptopProductServices;

    public LaptopProductUserController(LaptopProductServices laptopProductServices) {
        this.laptopProductServices = laptopProductServices;
    }


    @GetMapping
    public ResponseEntity<Object> getAllProductLaptop(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "4") int size) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, laptopProductServices.getAllProduct(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllProductLaptopById(@PathVariable("id") int id) {
        if (!laptopProductServices.checkExitsProduct(id)) {
            return ResponseHandel.generateResponse("Not found product", HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, laptopProductServices.getProductById(id));
    }

}
