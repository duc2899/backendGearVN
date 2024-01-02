package com.example.demo.controller.ApiPrivate.users;

import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.AddressNoteRequestDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.DeleteAddressNoteRequestDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.EditAddressNoteRequestDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.GetAddressNoteByIdUserRequestDTO;
import com.example.demo.service.AddressNoteService.AddressNoteService;
import com.example.demo.utilities.ResponseHandel;
import jakarta.validation.Valid;
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
    public ResponseEntity<Object> addAddressNote(@Valid @RequestBody AddressNoteRequestDTO addressNoteRequestDTO) {
        if (Objects.equals(addressNoteService.addAddressNote(addressNoteRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, addressNoteService.getAddressNoteForUser(addressNoteRequestDTO.getIdUser()));
        } else {
            return ResponseHandel.generateResponse(addressNoteService.addAddressNote(addressNoteRequestDTO), HttpStatus.BAD_REQUEST, null);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllAddressNoteByIdUser(@RequestBody GetAddressNoteByIdUserRequestDTO getAddressNoteByIdUserRequestDTO) {
        return ResponseHandel.generateResponse("success", HttpStatus.OK, addressNoteService.getAddressNoteForUser(getAddressNoteByIdUserRequestDTO.getIdUser()));
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteAddressNote(@Valid @RequestBody DeleteAddressNoteRequestDTO deleteAddressNoteRequestDTO) {
        if (Objects.equals(addressNoteService.deleteAddressNote(deleteAddressNoteRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success", HttpStatus.OK, addressNoteService.getAddressNoteForUser(deleteAddressNoteRequestDTO.getIdUser()));
        }
        return ResponseHandel.generateResponse(addressNoteService.deleteAddressNote(deleteAddressNoteRequestDTO), HttpStatus.BAD_REQUEST, null);
    }

    @PutMapping
    public ResponseEntity<Object> editAddressNote(@RequestBody EditAddressNoteRequestDTO editAddressNoteRequestDTO) {
        if (Objects.equals(addressNoteService.editAddressNote(editAddressNoteRequestDTO), "success")) {
            return ResponseHandel.generateResponse("success edit", HttpStatus.OK, addressNoteService.getAddressNoteForUser(editAddressNoteRequestDTO.getIdUser()));
        }
        return ResponseHandel.generateResponse(addressNoteService.editAddressNote(editAddressNoteRequestDTO), HttpStatus.BAD_REQUEST, null);
    }
}
