package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.service.ProducerServices.ProducerServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/private/admin/producer")
@CrossOrigin(origins = "**")
public class ProducerAdminController {

    private final ProducerServices producerServices;

    public ProducerAdminController(ProducerServices producerServices) {
        this.producerServices = producerServices;
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategory() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, producerServices.getAllProducer());
    }
}
