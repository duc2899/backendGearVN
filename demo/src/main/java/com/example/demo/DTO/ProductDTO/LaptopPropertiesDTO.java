package com.example.demo.DTO.ProductDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LaptopPropertiesDTO {
    private String cpu;
    private String vga;
    private String ram;
    private String ssd;
    private String screen;

    @JsonIgnore
    public boolean isLaptopPropertiesNull(){
        if(cpu != null){
            return false;
        }
        if(vga != null){
            return false;
        }
        if(ram != null){
            return false;
        }
        if(ssd != null){
            return false;
        }
        if(screen != null){
            return false;
        }
        return true;
    }
}
