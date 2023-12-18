package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.AcccountDTO.ChangeInforUserRequestDTO;
import com.example.demo.service.AccountUserServices.AccountUserServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/user/accountUsers")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountUsersProductController {
    private final AccountUserServices accountUserServices;

    public AccountUsersProductController(AccountUserServices accountUserServices){
        this.accountUserServices = accountUserServices;
    }
    @PutMapping
    public ResponseEntity<Object> editUser(@RequestBody ChangeInforUserRequestDTO changeInforUserRequestDTO){
        if (Objects.equals(accountUserServices.editUser(changeInforUserRequestDTO), "success")){
            return ResponseHandel.generateResponse("Edit Successfully", HttpStatus.OK, accountUserServices.getUserByID(changeInforUserRequestDTO.getId()));
        }else {
            return ResponseHandel.generateResponse(accountUserServices.editUser(changeInforUserRequestDTO), HttpStatus.NOT_FOUND, null);
        }
    }

}

