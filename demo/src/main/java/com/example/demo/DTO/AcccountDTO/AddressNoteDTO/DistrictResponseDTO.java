package com.example.demo.DTO.AcccountDTO.AddressNoteDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DistrictResponseDTO {
    private String name;
    private int code;
    private int id;
}
