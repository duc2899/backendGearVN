package com.example.demo.controller.ApiPublic;

import com.example.demo.DTO.AcccountDTO.*;
import com.example.demo.service.AccountUserServices.AccountUserServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "http://localhost:3000")
public class ForgotAndActiveController {
    public final AccountUserServices accountUserServices;

    public ForgotAndActiveController(AccountUserServices accountUserServices) {
        this.accountUserServices = accountUserServices;
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        int expiredTime = 5; //Thời gian hết hạn otp
        ForgotPasswordResponseDTO forgotPasswordResponseDTO = new ForgotPasswordResponseDTO();
        forgotPasswordResponseDTO.setExpiration(expiredTime);
        if (Objects.equals(accountUserServices.forGotPassWord(forgotPasswordDTO, expiredTime), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, forgotPasswordResponseDTO);
        } else {
            return ResponseHandel.generateResponse("User not found", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/checkOTP")
    public ResponseEntity<Object> checkOTP(@RequestBody CheckOTPDTO checkOTPDTO) {
        if (Objects.equals(accountUserServices.checkOTP(checkOTPDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(accountUserServices.checkOTP(checkOTPDTO), HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        if (Objects.equals(accountUserServices.changPassword(changePasswordRequestDTO), "success")) {
            return ResponseHandel.generateResponse("Change success", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(accountUserServices.changPassword(changePasswordRequestDTO), HttpStatus.NOT_FOUND, null);
        }
    }
}
