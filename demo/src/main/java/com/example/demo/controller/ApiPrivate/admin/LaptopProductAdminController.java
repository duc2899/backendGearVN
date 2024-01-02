package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.DTO.ProductDTO.EditLaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.LaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductPropertiesDTO;
import com.example.demo.service.ProductServices.LaptopProductServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/admin/laptopProduct")
@CrossOrigin(origins = "http://localhost:4000")
public class LaptopProductAdminController {
    private final LaptopProductServices laptopProductServices;

    public LaptopProductAdminController(LaptopProductServices laptopProductServices) {
        this.laptopProductServices = laptopProductServices;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProductLaptop() {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, laptopProductServices.getAllProduct());
    }

    //    @GetMapping("/{laptopProperties}")
//    public  ResponseEntity<Object> getProperties(@RequestBody ProductPropertiesDTO productPropertiesDTO){
//        return
//    }
    @PostMapping
    public ResponseEntity<Object> addProductLaptop(@RequestBody LaptopProductRequestDTO laptopProductRequestDTO) {
        if (Objects.equals(laptopProductServices.addLaptopProduct(laptopProductRequestDTO), "success")) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.CREATED, laptopProductServices.getAllProduct());
        } else {
            return ResponseHandel.generateResponse(laptopProductServices.addLaptopProduct(laptopProductRequestDTO), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductLaptop(@PathVariable("id") int id){
        if(laptopProductServices.removeLaptopProduct(id).equals("success")){
            return ResponseHandel.generateResponse("Delete successfully", HttpStatus.OK, laptopProductServices.getAllProduct());
        }else {
            return ResponseHandel.generateResponse(laptopProductServices.removeLaptopProduct(id), HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editProductLaptop(@RequestBody LaptopProductRequestDTO laptopProductRequestDTO, @PathVariable("id") int id){
        if(laptopProductServices.editLaptopProduct(id, laptopProductRequestDTO).equals("success")){
            return ResponseHandel.generateResponse("successfully", HttpStatus.OK, laptopProductServices.getAllProduct());
        }else {
            return ResponseHandel.generateResponse(laptopProductServices.editLaptopProduct(id, laptopProductRequestDTO), HttpStatus.BAD_REQUEST, null );
        }
    }


}
