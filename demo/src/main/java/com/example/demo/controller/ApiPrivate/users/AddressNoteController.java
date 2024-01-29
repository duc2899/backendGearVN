package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.AddressNoteRequestDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.DeleteAddressNoteRequestDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.EditAddressNoteRequestDTO;
import com.example.demo.service.AddressNoteService.AddressNoteService;
import com.example.demo.utilities.ResponseHandel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/public/user/addressNote")
@CrossOrigin(origins = "http://localhost:3000")
public class AddressNoteController {
    private final AddressNoteService addressNoteService;

    public AddressNoteController(AddressNoteService addressNoteService) {
        this.addressNoteService = addressNoteService;
    }

    @PostMapping
    public ResponseEntity<Object> addAddressNote(@RequestBody AddressNoteRequestDTO addressNoteRequestDTO) {
        String message = addressNoteService.addAddressNote(addressNoteRequestDTO);
        if (Objects.equals(message, "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        } else {
            return ResponseHandel.generateResponse(message, HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<Object> getAllAddressNoteByIdUser(@PathVariable int idUser) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, addressNoteService.getAddressNoteForUser(idUser));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAddressNote(@RequestBody DeleteAddressNoteRequestDTO deleteAddressNoteRequestDTO) {
        if (Objects.equals(addressNoteService.deleteAddressNote(deleteAddressNoteRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(addressNoteService.deleteAddressNote(deleteAddressNoteRequestDTO), HttpStatus.BAD_REQUEST, null);
    }

    @PutMapping
    public ResponseEntity<Object> editAddressNote(@RequestBody EditAddressNoteRequestDTO editAddressNoteRequestDTO) {
        if (Objects.equals(addressNoteService.editAddressNote(editAddressNoteRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success edit", HttpStatus.OK, null);
        }
        return ResponseHandel.generateResponse(addressNoteService.editAddressNote(editAddressNoteRequestDTO), HttpStatus.BAD_REQUEST, null);
    }
}
