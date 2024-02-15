package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.ProductDTO.FeedbackDTO.DeleteFeedbackRequestDTO;
import com.example.demo.DTO.ProductDTO.FeedbackDTO.EditFeedbackRequestDTO;
import com.example.demo.DTO.ProductDTO.FeedbackDTO.FeedbackRequestDTO;
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
        String message = productFeedbackService.createFeedback(feedbackRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getFeedbacks(@PathVariable int id, @RequestParam int page, @RequestParam int size) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, productFeedbackService.getFeedbacksByProduct(id, page, size));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteFeedback(@RequestBody DeleteFeedbackRequestDTO deleteFeedbackRequestDTO) {
        String message = productFeedbackService.deleteFeedbackProduct(deleteFeedbackRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.NOT_FOUND, null);
    }


    @PutMapping
    public ResponseEntity<Object> editFeedback(@RequestBody EditFeedbackRequestDTO editFeedbackRequestDTO) {
        String message = productFeedbackService.editFeedbackProduct(editFeedbackRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }
}
