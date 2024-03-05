package com.example.demo.controller.ApiPublic;

import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductKeyBoardRequestDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductLaptopRequestDTO;
import com.example.demo.DTO.ProductDTO.SearchProductDTO.SearchProductMouseRequestDTO;
import com.example.demo.controller.SearchServices.SearchService;
import com.example.demo.modal.ProductModalPackage.ProductType;
import com.example.demo.service.ProductServices.ProductServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/user/product")
@CrossOrigin(origins = "**")
public class ProductUserController {
    private final ProductServices productServices;
    private final SearchService searchService;

    public ProductUserController(ProductServices productServices, SearchService searchService) {
        this.productServices = productServices;
        this.searchService = searchService;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Object> getAllProductLaptop(@PathVariable String name) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productServices.getListProductByName(name));
    }

    @GetMapping("/laptop")
    public ResponseEntity<Object> getAllProductMouse(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "4") int size) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productServices.getAllProduct(page, size, 1));
    }

    @GetMapping("/mouse")
    public ResponseEntity<Object> getAllProductLaptop(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productServices.getAllProduct(page, size, 2));
    }

    @GetMapping("/keyboard")
    public ResponseEntity<Object> getAllProductKeyBoard(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productServices.getAllProduct(page, size, 3));
    }

    @GetMapping("/laptop/{id}")
    public ResponseEntity<Object> getAllProductLaptopById(@PathVariable("id") int id) {
        ProductResponseDTO productResponseDTO = productServices.getProductById(id, 1);
        if (productResponseDTO == null) {
            return ResponseHandel.generateResponse("Not found product", HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productResponseDTO);
    }

    @GetMapping("/mouse/{id}")
    public ResponseEntity<Object> getAllProductMouseById(@PathVariable("id") int id) {
        ProductResponseDTO productResponseDTO = productServices.getProductById(id, 2);
        if (productResponseDTO == null) {
            return ResponseHandel.generateResponse("Not found product", HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productResponseDTO);
    }


    @GetMapping("/getImagesBanner/{limit}")
    public ResponseEntity<Object> getImagesBanner(@PathVariable("limit") int limit) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productServices.getListImageBanner(limit));
    }


    @PostMapping("/search/laptop")
    public ResponseEntity<Object> searchProductLaptop(@RequestBody SearchProductLaptopRequestDTO searchProductLaptopRequestDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, searchService.searchProductLaptop(searchProductLaptopRequestDTO, page, size));
    }

    @PostMapping("/search/keyboard")
    public ResponseEntity<Object> searchProductKeyboard(@RequestBody SearchProductKeyBoardRequestDTO searchProductKeyBoardRequestDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, searchService.searchProductKeyboard(searchProductKeyBoardRequestDTO, page, size));
    }

    @PostMapping("/search/mouse")
    public ResponseEntity<Object> searchProductMouse(@RequestBody SearchProductMouseRequestDTO searchProductMouseRequestDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, searchService.searchProductMouse(searchProductMouseRequestDTO, page, size));
    }

    @GetMapping("/sort/price")
    public ResponseEntity<Object> sortProductLaptop(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size, @RequestParam ProductType type, @RequestParam String sort) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, searchService.sortProduct(sort, page, size, type));
    }

}
