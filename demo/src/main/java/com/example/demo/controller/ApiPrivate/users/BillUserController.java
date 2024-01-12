package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.BillDTO.BillRequestDTO;
import com.example.demo.DTO.BillDTO.BillResponseDTO;
import com.example.demo.DTO.BillDTO.FindBillByIdUserAndIdBillRequestDTO;
import com.example.demo.DTO.BillDTO.GetBillsByUserRequestDTO;
import com.example.demo.modal.BillModalPackage.PaymentType;
import com.example.demo.service.BillService.BillService;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("api/public/user/bill")
@CrossOrigin(origins = "http://localhost:3000")
public class BillUserController {
    private final BillService billService;

    public BillUserController(BillService billService) {
        this.billService = billService;
    }

    @PostMapping
    public ResponseEntity<Object> createBill(@RequestBody BillRequestDTO billRequestDTO) {
//        if(billRequestDTO.getPaymentType() != PaymentType.COD && billRequestDTO.getPaymentType() != PaymentType.ONLINE){
//            return ResponseHandel.generateResponse("fault fiwefiawef", HttpStatus.BAD_REQUEST, null);
//        }
        if (Objects.equals(billService.createBill(billRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse("fault", HttpStatus.BAD_REQUEST, null);
    }

    @GetMapping
    public ResponseEntity<Object> getBillsByUser(@RequestBody GetBillsByUserRequestDTO getBillsByUserRequestDTO) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, billService.getListBillByUser(getBillsByUserRequestDTO));
    }

    @GetMapping("/findBillByIdUserAndIdBill")
    public ResponseEntity<Object> getAllBillsByIdUserAndIdBill(@RequestBody FindBillByIdUserAndIdBillRequestDTO findBillByIdUserAndIdBillRequestDTO) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, billService.getBillByIdUserAndIdBill(findBillByIdUserAndIdBillRequestDTO));
    }
}
