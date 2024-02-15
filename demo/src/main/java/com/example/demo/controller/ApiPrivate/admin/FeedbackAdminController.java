package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.DTO.ProductDTO.FeedbackDTO.DeleteFeedbackRequestDTO;
import com.example.demo.service.ProductFeedbackService.ProductFeedbackService;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/private/admin/feedbackProduct")
@CrossOrigin(origins = "http://localhost:4000")
@Validated
public class FeedbackAdminController {
    private final ProductFeedbackService productFeedbackService;

    public FeedbackAdminController(ProductFeedbackService productFeedbackService) {
        this.productFeedbackService = productFeedbackService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllFeedbackProduct() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, productFeedbackService.getAllProductFeedback());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteFeedback(@RequestBody DeleteFeedbackRequestDTO deleteFeedbackRequestDTO) {
        String message = productFeedbackService.deleteFeedbackProduct(deleteFeedbackRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.NOT_FOUND, null);
    }
}
