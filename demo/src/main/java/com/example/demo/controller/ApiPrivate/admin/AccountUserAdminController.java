package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.DTO.AcccountDTO.ChangeInforUserRequestDTO;
import com.example.demo.DTO.AcccountDTO.RegisterRequestDTO;
import com.example.demo.service.AccountUserServices.AccountUserServices;
import com.example.demo.service.AuthenticateService.AuthenticateServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/private/admin/accountUsers")
@CrossOrigin(origins = "**")
public class AccountUserAdminController {
    private final AccountUserServices accountUserServices;
    private final AuthenticateServices authenticateServices;

    public AccountUserAdminController(AccountUserServices accountUserServices, AuthenticateServices authenticateServices) {
        this.accountUserServices = accountUserServices;
        this.authenticateServices = authenticateServices;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, accountUserServices.getAllUsers());
    }

    @GetMapping("/{email}")
    public ResponseEntity<Object> searchUserByEmail(@PathVariable("email") String email){
        if(Objects.equals(accountUserServices.findUserByEmail(email), "Exits user")){
            return ResponseHandel.generateResponse("Exits user", HttpStatus.NOT_FOUND, null);
        }else {
            return ResponseHandel.generateResponse("Not exits user", HttpStatus.OK, null);
        }
    }
    @PostMapping
    public ResponseEntity<Object> addAccount(@RequestBody RegisterRequestDTO registerRequest) {
        if (Objects.equals(authenticateServices.checkRegister(registerRequest), "success")) {
            return ResponseHandel.generateResponse("Add User successfully", HttpStatus.CREATED, authenticateServices.register(registerRequest));
        } else {
            return ResponseHandel.generateResponse(authenticateServices.checkRegister(registerRequest), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        if (accountUserServices.deleteUser(id).equals("success")) {
            return ResponseHandel.generateResponse("Delete Successfully", HttpStatus.OK, accountUserServices.getAllUsers());
        } else {
            return ResponseHandel.generateResponse(accountUserServices.deleteUser(id), HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping
    public ResponseEntity<Object> editUser(@RequestBody ChangeInforUserRequestDTO changeInforUserRequestDTO) {
        if (Objects.equals(accountUserServices.editUser(changeInforUserRequestDTO), "success")) {
            return ResponseHandel.generateResponse("Edit Successfully", HttpStatus.OK, accountUserServices.getUserByID(changeInforUserRequestDTO.getId()));
        } else {
            return ResponseHandel.generateResponse(accountUserServices.editUser(changeInforUserRequestDTO), HttpStatus.NOT_FOUND, null);
        }
    }

}
