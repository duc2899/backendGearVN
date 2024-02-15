package com.example.demo.DTO.ProductDTO.ProductKeyBoardDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class KeyBoardPropertiesDTO {
    @NotNull(message = "size must be not null")
    @NotBlank(message = "size must be not blank")
    private String size;
    @NotNull(message = "connection must be not null")
    private Boolean connection;
    @NotNull(message = "charger must be not null")
    private Boolean charger;
    @NotNull(message = "rgb must be not null")
    private Boolean rgb;
    @NotNull(message = "color must be not null")
    @NotBlank(message = "color must be not blank")
    private String color;
    @NotNull(message = "expand must be not null")
    @NotBlank(message = "expand must be not blank")
    private String expand;
    @NotNull(message = "material must be not null")
    @NotBlank(message = "material must be not blank")
    private String material;
    @NotNull(message = "switches must be not null")
    @NotBlank(message = "switches must be not blank")
    private String switches;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean getConnection() {
        return connection;
    }

    public void setConnection(Boolean connection) {
        this.connection = connection;
    }

    public Boolean getCharger() {
        return charger;
    }

    public void setCharger(Boolean charger) {
        this.charger = charger;
    }

    public Boolean getRgb() {
        return rgb;
    }

    public void setRgb(Boolean rgb) {
        this.rgb = rgb;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSwitches() {
        return switches;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }
}
