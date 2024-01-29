package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.BillDTO.BillRequestDTO;
import com.example.demo.DTO.BillDTO.BillResponseDTO;
import com.example.demo.DTO.BillDTO.CancelBillRequestDTO;
import com.example.demo.DTO.BillDTO.FindBillByIdUserAndIdBillRequestDTO;
import com.example.demo.service.BillService.BillService;
import com.example.demo.utilities.ResponseHandel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("api/public/user/bill")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class BillUserController {
    private final BillService billService;

    public BillUserController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Object> createBill(@RequestBody @Valid BillRequestDTO billRequestDTO) {
        BillResponseDTO billResponseDTO = billService.createBill(billRequestDTO);
        if (billResponseDTO == null) {
            return ResponseHandel.generateResponse("Error cannot create bill", HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandel.generateResponse("success", HttpStatus.OK, billResponseDTO);
    }

    @PostMapping("/cancelBill")
    public ResponseEntity<Object> cancelBill(@RequestBody @Valid CancelBillRequestDTO cancelBillRequestDTO) {
        String message = billService.cancelBill(cancelBillRequestDTO.getIdBill());
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> getBillsByUser(@PathVariable int idUser) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, billService.getListBillByUser(idUser));
    }

    @GetMapping("/findBillByIdUserAndIdBill")
    public ResponseEntity<Object> getAllBillsByIdUserAndIdBill(@RequestBody @Valid FindBillByIdUserAndIdBillRequestDTO findBillByIdUserAndIdBillRequestDTO) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, billService.getBillByIdUserAndIdBill(findBillByIdUserAndIdBillRequestDTO));
    }

}
