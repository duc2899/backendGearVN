package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.AcccountDTO.CartDTO.CartRequestDTO;
import com.example.demo.service.CartServices.CartServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/user/cart")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class CartController {
    private final CartServices cartServices;

    public CartController(CartServices cartServices) {
        this.cartServices = cartServices;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> getCartByUser(@PathVariable int idUser) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, cartServices.getCartUser(idUser));
    }

    @PostMapping
    public ResponseEntity<Object> actionCart(@RequestBody CartRequestDTO cartRequestDTO) {
        if (Objects.equals(cartServices.addToCart(cartRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(cartServices.addToCart(cartRequestDTO), HttpStatus.BAD_REQUEST, null);
        }
    }

}
