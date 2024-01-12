package com.example.demo.controller.ApiPrivate.admin;

import com.example.demo.DTO.ProductDTO.ProductLaptopDTO.LaptopProductRequestDTO;
import com.example.demo.service.CloudinaryService.CloudinaryService;
import com.example.demo.service.PreviewImageService.PreviewImageService;
import com.example.demo.service.ProductServices.LaptopProductServices;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/public/admin/laptopProduct")
@CrossOrigin(origins = "http://localhost:4000")
public class LaptopProductAdminController {
    private final LaptopProductServices laptopProductServices;
    private final CloudinaryService cloudinaryService;
    private final PreviewImageService previewImageService;

    public LaptopProductAdminController(LaptopProductServices laptopProductServices, CloudinaryService cloudinaryService, PreviewImageService previewImageService) {
        this.laptopProductServices = laptopProductServices;
        this.cloudinaryService = cloudinaryService;
        this.previewImageService = previewImageService;
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile multipartFile, @PathVariable("id") int id) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return ResponseHandel.generateResponse("Image not validate", HttpStatus.BAD_REQUEST, null);
        }
        Map result = cloudinaryService.upload(multipartFile);
        if (!Objects.equals(laptopProductServices.uploadImage(result.get("url").toString(), id), "success")) {
            return ResponseHandel.generateResponse(laptopProductServices.uploadImage(result.get("url").toString(), id), HttpStatus.BAD_REQUEST, null);
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
                        previewImageService.addPreviewImage(id, result.get("url").toString());
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

    @GetMapping
    public ResponseEntity<Object> getAllProductLaptop(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "4") int size) {
        return ResponseHandel.generateResponse("successfully", HttpStatus.OK, laptopProductServices.getAllProduct(page, size));
    }

    //    @GetMapping("/{laptopProperties}")
//    public  ResponseEntity<Object> getProperties(@RequestBody ProductPropertiesDTO productPropertiesDTO){
//        return
//    }
    @PostMapping
    public ResponseEntity<Object> addProductLaptop(@RequestBody LaptopProductRequestDTO laptopProductRequestDTO) {
        if (Objects.equals(laptopProductServices.addLaptopProduct(laptopProductRequestDTO), "success")) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.CREATED, null);
        } else {
            return ResponseHandel.generateResponse(laptopProductServices.addLaptopProduct(laptopProductRequestDTO), HttpStatus.BAD_REQUEST, null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProductLaptop(@PathVariable("id") int id) {
        if (laptopProductServices.removeLaptopProduct(id).equals("success")) {
            return ResponseHandel.generateResponse("Delete successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(laptopProductServices.removeLaptopProduct(id), HttpStatus.NOT_FOUND, null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editProductLaptop(@RequestBody LaptopProductRequestDTO laptopProductRequestDTO, @PathVariable("id") int id) {
        if (laptopProductServices.editLaptopProduct(id, laptopProductRequestDTO).equals("success")) {
            return ResponseHandel.generateResponse("successfully", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(laptopProductServices.editLaptopProduct(id, laptopProductRequestDTO), HttpStatus.BAD_REQUEST, null);
        }
    }


}
