package com.example.demo.controller.ApiPublic;

import com.example.demo.DTO.AcccountDTO.LoginRequestDTO;
import com.example.demo.DTO.AcccountDTO.RegisterRequestDTO;
import com.example.demo.service.AuthenticateService.AuthenticateServices;
import com.example.demo.utilities.ResponseHandel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginAndRegisterController {
    private final AuthenticateServices authenticateServices;

    public LoginAndRegisterController(AuthenticateServices authenticateServices) {
        this.authenticateServices = authenticateServices;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        if (Objects.equals(authenticateServices.checkLogin(loginRequestDTO), "success")) {
            return ResponseHandel.generateResponse("Login successfully", HttpStatus.OK, authenticateServices.login(loginRequestDTO));
        } else {
            return ResponseHandel.generateResponse(authenticateServices.checkLogin(loginRequestDTO), HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequestDTO registerRequest) {
        if (Objects.equals(authenticateServices.checkRegister(registerRequest), "success")) {
            return ResponseHandel.generateResponse("Register successfully", HttpStatus.CREATED, authenticateServices.register(registerRequest));
        } else {
            return ResponseHandel.generateResponse(authenticateServices.checkRegister(registerRequest), HttpStatus.NOT_FOUND, null);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
            return ResponseHandel.generateResponse("Logout success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse("Logout fault", HttpStatus.NOT_FOUND, null);
    }

    @PostMapping("/login/admin")
    public ResponseEntity<Object> loginAdmin(@RequestBody LoginRequestDTO loginRequestDTO) {
        if (Objects.equals(authenticateServices.checkLoginAdmin(loginRequestDTO), "success")) {
            return ResponseHandel.generateResponse("Login Admin successfully", HttpStatus.OK, authenticateServices.login(loginRequestDTO));
        } else {
            return ResponseHandel.generateResponse(authenticateServices.checkLoginAdmin(loginRequestDTO), HttpStatus.NOT_FOUND, null);
        }
    }

}
