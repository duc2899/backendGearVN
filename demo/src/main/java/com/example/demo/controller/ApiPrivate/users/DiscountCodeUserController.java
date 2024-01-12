package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.DiscountCodeDTO.CheckDiscountCodeRequestDTO;
import com.example.demo.service.DiscountCodeService.DiscountCodeService;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/user/discountCode")
@CrossOrigin(origins = "http://localhost:3000")
public class DiscountCodeUserController {
    private final DiscountCodeService discountCodeService;

    public DiscountCodeUserController(DiscountCodeService discountCodeService) {
        this.discountCodeService = discountCodeService;
    }

    @GetMapping
    public ResponseEntity<Object> getListDiscountCode() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, discountCodeService.getListDCResponseDTOS());
    }


    @PostMapping
    public ResponseEntity<Object> checkDiscountCode(@RequestBody CheckDiscountCodeRequestDTO checkDiscountCodeRequestDTO) {
        if (Objects.equals(discountCodeService.checkDiscountCode(checkDiscountCodeRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(discountCodeService.checkDiscountCode(checkDiscountCodeRequestDTO), HttpStatus.BAD_REQUEST, null);
    }
}
