package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.DTO.CloudinaryDTO.DeleteCloudinaryRequestDTO;
import com.example.demo.DTO.ProductDTO.DeleteProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductKeyBoardDTO.KeyBoardProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductMouseDTO.MouseProductRequestDTO;
import com.example.demo.DTO.ProductDTO.ProductResponseDTO;
import com.example.demo.service.CloudinaryService.CloudinaryService;
import com.example.demo.service.PreviewImageService.PreviewImageService;
import com.example.demo.service.ProductServices.ProductServices;
import com.example.demo.utilities.ResponseHandel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/private/admin/product")
@CrossOrigin(origins = "http://localhost:4000")
@Validated
public class ProductAdminController {
    private final CloudinaryService cloudinaryService;
    private final PreviewImageService previewImageService;
    private final ProductServices productServices;

    public ProductAdminController(CloudinaryService cloudinaryService, PreviewImageService previewImageService, ProductServices productServices) {
        this.cloudinaryService = cloudinaryService;
        this.previewImageService = previewImageService;
        this.productServices = productServices;
    }

    @GetMapping
    public ResponseEntity<Object> getAllProductLaptop(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "4") int size, @RequestParam int type) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, productServices.getAllProduct(page, size, type));
    }

    @PostMapping("/uploadImage/{id}")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") int id) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return ResponseHandel.generateResponse("Image not validate", HttpStatus.BAD_REQUEST, null);
        }
        Map result = cloudinaryService.upload(multipartFile);
        String message = productServices.uploadImage(result.get("url").toString(), id);
        if (!Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
        }
        return ResponseHandel.generateResponse("success save image ", HttpStatus.OK, null);
    }

    @PostMapping("/uploadPreviewImage/{id}")
    public ResponseEntity<Object> uploadPreviewImage(@RequestParam("file") MultipartFile[] files, @PathVariable("id") int id) throws IOException {

        try {
            Arrays.stream(files).forEach(file -> {
                try {
                    BufferedImage bi = ImageIO.read(file.getInputStream());
                    if (bi != null) {
                        Map result = cloudinaryService.upload(file);
                        previewImageService.addPreviewImage(id, result.get("url").toString(), result.get("public_id").toString());
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            return ResponseHandel.generateResponse("success save preview images", HttpStatus.OK, null);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseHandel.generateResponse("Fail to upload files!", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/deleteImage")
    public ResponseEntity<Object> deleteImage(@RequestBody DeleteCloudinaryRequestDTO deleteCloudinaryRequestDTO) throws IOException {
        cloudinaryService.delete(deleteCloudinaryRequestDTO.getId());
        return ResponseHandel.generateResponse("success save preview images", HttpStatus.OK, null);
    }

    @PostMapping("/addLaptopProduct")
    public ResponseEntity<Object> addProductLaptop(@RequestBody LaptopProductRequestDTO laptopProductRequestDTO) {
        ProductResponseDTO productResponseDTO = productServices.addLaptopProduct(laptopProductRequestDTO);
        if (productResponseDTO != null) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.CREATED, productResponseDTO);
        } else {
            return ResponseHandel.generateResponse("fault", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/addMouseProduct")
    public ResponseEntity<Object> addProductMouse(@RequestBody @Valid MouseProductRequestDTO mouseProductRequestDTO) {
        ProductResponseDTO productResponseDTO = productServices.addMouseProduct(mouseProductRequestDTO);
        if (productResponseDTO != null) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.CREATED, productResponseDTO);
        } else {
            return ResponseHandel.generateResponse("fault", HttpStatus.BAD_REQUEST, null);
        }
    }

    @PostMapping("/addKeyboardProduct")
    public ResponseEntity<Object> addProductKeyboard(@RequestBody @Valid KeyBoardProductRequestDTO keyBoardProductRequestDTO) {
        ProductResponseDTO productResponseDTO = productServices.addKeyBoardProduct(keyBoardProductRequestDTO);
        if (productResponseDTO != null) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.CREATED, productResponseDTO);
        } else {
            return ResponseHandel.generateResponse("fault", HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteProduct(@RequestBody DeleteProductRequestDTO deleteProductRequestDTO) throws IOException {
        String message = productServices.removeProduct(deleteProductRequestDTO);
        if (message.equals("success")) {
            return ResponseHandel.generateResponse("Delete successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(message, HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/editLaptopProduct/{id}")
    public ResponseEntity<Object> editProductLaptop(@RequestBody LaptopProductRequestDTO laptopProductRequestDTO, @PathVariable("id") int id) {
        String message = productServices.editLaptopProduct(id, laptopProductRequestDTO);
        if (message.equals("success")) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping("/editKeyboardProduct/{id}")
    public ResponseEntity<Object> editProductKeyboard(@RequestBody KeyBoardProductRequestDTO keyBoardProductRequestDTO, @PathVariable("id") int id) {
        String message = productServices.editKeyboardProduct(id, keyBoardProductRequestDTO);
        if (message.equals("success")) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
        }
    }

    @PutMapping("/editMouseProduct/{id}")
    public ResponseEntity<Object> editMouseLaptop(@RequestBody MouseProductRequestDTO mouseProductRequestDTO, @PathVariable("id") int id) {
        String message = productServices.editMouseProduct(id, mouseProductRequestDTO);
        if (message.equals("success")) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
        }
    }
}
