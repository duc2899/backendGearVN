package com.example.demo.controller.ApiPublic;

import com.example.demo.DTO.AcccountDTO.ChangePasswordRequestDTO;
import com.example.demo.DTO.AcccountDTO.CheckOTPDTO;
import com.example.demo.DTO.AcccountDTO.ForgotPasswordDTO;
import com.example.demo.DTO.AcccountDTO.ForgotPasswordResponseDTO;
import com.example.demo.service.AccountUserServices.AccountUserServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "**")
public class ForgotAndActiveController {
    public final AccountUserServices accountUserServices;

    public ForgotAndActiveController(AccountUserServices accountUserServices) {
        this.accountUserServices = accountUserServices;
    }

    @PostMapping("/forgotPassword")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
        int expiredTime = 1; //Thời gian hết hạn otp
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
        String message = accountUserServices.checkOTP(checkOTPDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(message, HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(@RequestBody ChangePasswordRequestDTO changePasswordRequestDTO) {
        String message = accountUserServices.changPassword(changePasswordRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("Change success", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(message, HttpStatus.NOT_FOUND, null);
        }
    }
}
