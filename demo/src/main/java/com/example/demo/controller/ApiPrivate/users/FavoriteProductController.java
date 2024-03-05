package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.ProductDTO.FavoriteProductDTO.CreateFavoriteProductRequestDTO;
import com.example.demo.service.FavoriteProductServices.FavoriteProductServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/user/favoriteProduct")
@CrossOrigin(origins = "http://localhost:3000")
@Validated
public class FavoriteProductController {
    private final FavoriteProductServices favoriteProductServices;

    public FavoriteProductController(FavoriteProductServices favoriteProductServices) {
        this.favoriteProductServices = favoriteProductServices;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> getFavoriteProduct(@PathVariable int idUser) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, favoriteProductServices.getListFavoriteProductByUser(idUser));
    }

    @PostMapping
    public ResponseEntity<Object> crateFavoriteProduct(@RequestBody CreateFavoriteProductRequestDTO createFavoriteProductRequestDTO) {
        String message = favoriteProductServices.crateFavoriteProduct(createFavoriteProductRequestDTO);
        if (message.equals("success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFavoriteProduct(@PathVariable int id) {
        String message = favoriteProductServices.deleteFavoriteProduct(id);
        if (message.equals("success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
    }
}
