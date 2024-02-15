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
@RequestMapping("api/private/admin/discountCode")
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
        String message = discountCodeService.createDiscountCode(createDCRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.CREATED, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }

    @PutMapping
    public ResponseEntity<Object> editDiscountCode(@RequestBody EditDCRequestDTO editDCRequestDTO) {
        String message = discountCodeService.editDiscountCode(editDCRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, discountCodeService.getListDCResponseDTOS());
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteDiscountCode(@RequestBody DeleteDCRequestDTO deleteDCRequestDTO) {
        String message = discountCodeService.deleteDiscountCode(deleteDCRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, discountCodeService.getListDCResponseDTOS());
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }
}
