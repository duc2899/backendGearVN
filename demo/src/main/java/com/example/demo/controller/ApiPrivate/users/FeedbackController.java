package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.ProductDTO.FeedbackDTO.DeleteFeedbackRequestDTO;
import com.example.demo.DTO.ProductDTO.FeedbackDTO.EditFeedbackRequestDTO;
import com.example.demo.DTO.ProductDTO.FeedbackDTO.FeedbackRequestDTO;
import com.example.demo.DTO.ProductDTO.FeedbackDTO.GetFeedbackRequestDTO;
import com.example.demo.service.ProductFeedbackService.ProductFeedbackService;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/user/feedbackProduct")
@CrossOrigin(origins = "http://localhost:3000")
public class FeedbackController {
    private final ProductFeedbackService productFeedbackService;

    public FeedbackController(ProductFeedbackService productFeedbackService) {
        this.productFeedbackService = productFeedbackService;
    }

    @PostMapping
    public ResponseEntity<Object> createFeedback(@RequestBody FeedbackRequestDTO feedbackRequestDTO) {
        if (Objects.equals(productFeedbackService.createFeedback(feedbackRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(productFeedbackService.createFeedback(feedbackRequestDTO), HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping
    public ResponseEntity<Object> getFeedbacks(@RequestBody GetFeedbackRequestDTO getFeedbackRequestDTO) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, productFeedbackService.getFeedbacksByProduct(getFeedbackRequestDTO.getIdProduct()));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteFeedback(@RequestBody DeleteFeedbackRequestDTO deleteFeedbackRequestDTO) {
        if (Objects.equals(productFeedbackService.deleteFeedbackProduct(deleteFeedbackRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, productFeedbackService.getFeedbacksByProduct(deleteFeedbackRequestDTO.getIdProduct()));
        }
        return ResponseHandel.generateResponse(productFeedbackService.deleteFeedbackProduct(deleteFeedbackRequestDTO), HttpStatus.NOT_FOUND, null);
    }


    @PutMapping
    public ResponseEntity<Object> editFeedback(@RequestBody EditFeedbackRequestDTO editFeedbackRequestDTO) {
        if (Objects.equals(productFeedbackService.editFeedbackProduct(editFeedbackRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, productFeedbackService.getFeedbacksByProduct(editFeedbackRequestDTO.getIdProduct()));
        }
        return ResponseHandel.generateResponse(productFeedbackService.editFeedbackProduct(editFeedbackRequestDTO), HttpStatus.BAD_REQUEST, null);
    }
}
