package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.DTO.BillDTO.EditPaymentBillRequestDTO;
import com.example.demo.DTO.BillDTO.EditStatusBillRequestDTO;
import com.example.demo.DTO.BillDTO.FindBillByIdUserAndIdBillRequestDTO;
import com.example.demo.service.BillService.BillService;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/admin/bill")
@CrossOrigin(origins = "http://localhost:4000")
public class BillAdminController {
    private final BillService billService;

    public BillAdminController(BillService billService) {
        this.billService = billService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllBills() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, billService.getListBills());
    }


    @PutMapping("/editStatus")
    public ResponseEntity<Object> editStatusBill(@RequestBody EditStatusBillRequestDTO editStatusBillRequestDTO) {
        String bill = billService.editStatusBill(editStatusBillRequestDTO);
        return ResponseHandel.generateResponse(bill, Objects.equals(bill, "success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST, null);
    }

    @PutMapping("/editIsPay")
    public ResponseEntity<Object> editPaymentBill(@RequestBody EditPaymentBillRequestDTO editPaymentBillRequestDTO) {
        String result = billService.paymentProcessing(editPaymentBillRequestDTO);
        return ResponseHandel.generateResponse(result, Objects.equals(result, "success") ? HttpStatus.OK : HttpStatus.BAD_REQUEST, null);
    }
}
