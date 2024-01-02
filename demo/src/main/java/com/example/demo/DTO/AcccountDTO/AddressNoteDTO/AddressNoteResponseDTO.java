package com.example.demo.DTO.AcccountDTO.AddressNoteDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressNoteResponseDTO {
    private int id;
    private String nameCustomer;
    private String homeAddress;
    private String phoneNumber;
    private WardResponseDTO ward;
    private DistrictResponseDTO district;
    private CityResponseDTO city;
}
