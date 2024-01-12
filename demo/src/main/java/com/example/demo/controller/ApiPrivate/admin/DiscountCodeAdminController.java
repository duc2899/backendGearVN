package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.DTO.DiscountCodeDTO.CreateDCRequestDTO;
import com.example.demo.DTO.DiscountCodeDTO.DeleteDCRequestDTO;
import com.example.demo.DTO.DiscountCodeDTO.EditDCRequestDTO;
import com.example.demo.service.DiscountCodeService.DiscountCodeService;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/admin/discountCode")
@CrossOrigin(origins = "http://localhost:4000")
public class DiscountCodeAdminController {
    private final DiscountCodeService discountCodeService;

    public DiscountCodeAdminController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @GetMapping
    public ResponseEntity<Object> getListDiscountCode() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, discountCodeService.getListDCResponseDTOS());
    }

    @PostMapping
    public ResponseEntity<Object> createDiscountCode(@RequestBody CreateDCRequestDTO createDCRequestDTO) {
        if (Objects.equals(discountCodeService.createDiscountCode(createDCRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, discountCodeService.getListDCResponseDTOS());
        }
        return ResponseHandel.generateResponse(discountCodeService.createDiscountCode(createDCRequestDTO), HttpStatus.BAD_REQUEST, null);
    }

    @PutMapping
    public ResponseEntity<Object> editDiscountCode(@RequestBody EditDCRequestDTO editDCRequestDTO) {
        if (Objects.equals(discountCodeService.editDiscountCode(editDCRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, discountCodeService.getListDCResponseDTOS());
        }
        return ResponseHandel.generateResponse(discountCodeService.editDiscountCode(editDCRequestDTO), HttpStatus.BAD_REQUEST, null);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteDiscountCode(@RequestBody DeleteDCRequestDTO deleteDCRequestDTO) {
        if (Objects.equals(discountCodeService.deleteDiscountCode(deleteDCRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, discountCodeService.getListDCResponseDTOS());
        }
        return ResponseHandel.generateResponse(discountCodeService.deleteDiscountCode(deleteDCRequestDTO), HttpStatus.BAD_REQUEST, null);
    }
}
