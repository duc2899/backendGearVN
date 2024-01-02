package com.example.demo.service.AddressNoteService;

import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.AddressNoteRequestDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.AddressNoteResponseDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.DeleteAddressNoteRequestDTO;
import com.example.demo.DTO.AcccountDTO.AddressNoteDTO.EditAddressNoteRequestDTO;
import com.example.demo.modal.AddressNotePackage.AddressNoteModal;
import com.example.demo.repository.AddressNoteRepository.AddressNoteRepository;
import com.example.demo.repository.AddressNoteRepository.CityRepository;
import com.example.demo.repository.AddressNoteRepository.DistrictRepository;
import com.example.demo.repository.AddressNoteRepository.WardRepository;
import com.example.demo.repository.UsersRepository.UserRepository;
import com.example.demo.utilities.TransferAddressNote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressNoteService {
    private final AddressNoteRepository addressNoteRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final DistrictRepository districtRepository;
    private final WardRepository wardRepository;

    public List<AddressNoteResponseDTO> getAddressNoteForUser(int idUser) {
        List<AddressNoteModal> listAddressNote = addressNoteRepository.findAddressNoteByIdUser(idUser);
        List<AddressNoteResponseDTO> result = new ArrayList<>();
        for (AddressNoteModal addressNoteModal : listAddressNote) {
            result.add(TransferAddressNote.toAddressResponseDTO(addressNoteModal));
        }
        return result;
    }

    public String addAddressNote(AddressNoteRequestDTO addressNoteRequestDTO) {
        if (userRepository.findById(addressNoteRequestDTO.getIdUser()).isEmpty()) {
            return "User not found";
        }
        if (isValidPhone(addressNoteRequestDTO.getPhoneNumber())) {
            return "Invalid phone number";
        }
        addressNoteRepository.save(TransferAddressNote.toAddressNoteModal(addressNoteRequestDTO, userRepository));
        return "success";
    }

    public String deleteAddressNote(DeleteAddressNoteRequestDTO deleteAddressNoteRequestDTO) {
        if (addressNoteRepository.findAddressNoteByIdUserAndIdAddressNote(deleteAddressNoteRequestDTO.getIdUser(), deleteAddressNoteRequestDTO.getIdAddress()).isEmpty()) {
            return "Not found address note";
        }
        addressNoteRepository.deleteById(deleteAddressNoteRequestDTO.getIdAddress());

        if (addressNoteRepository.findById(deleteAddressNoteRequestDTO.getIdAddress()).isEmpty()) {
            return "success";
        } else {
            return "fault to delete address note";
        }
    }

    public String editAddressNote(EditAddressNoteRequestDTO editAddressNoteRequestDTO) {
        if (addressNoteRepository.findAddressNoteByIdUserAndIdAddressNote(editAddressNoteRequestDTO.getIdUser(), editAddressNoteRequestDTO.getIdAddressNote()).isEmpty()) {
            return "Not found address note";
        }
        if (isValidPhone(editAddressNoteRequestDTO.getPhoneNumber())) {
            return "Invalid phone number";
        }
        addressNoteRepository.saveAndFlush(TransferAddressNote.toAddressNoteModal(editAddressNoteRequestDTO, addressNoteRepository, cityRepository, districtRepository, wardRepository, userRepository));
        return "success";
    }

    private boolean isValidPhone(String str) {
        // regex for phonenumber vietnam
        String reg = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";

        return !str.matches(reg);

    }
}
