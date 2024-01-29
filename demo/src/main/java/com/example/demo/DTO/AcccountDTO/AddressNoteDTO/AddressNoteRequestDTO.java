package com.example.demo.DTO.AcccountDTO.AddressNoteDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressNoteRequestDTO {
    private int idUser;
    private String nameCustomer;
    private String homeAddress;
    private String phoneNumber;
    private WardRequestDTO ward;
    private DistrictRequestDTO district;
    private CityRequestDTO city;

}

