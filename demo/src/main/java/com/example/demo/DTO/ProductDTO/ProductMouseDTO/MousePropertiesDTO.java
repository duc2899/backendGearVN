package com.example.demo.DTO.ProductDTO.ProductMouseDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MousePropertiesDTO {
    @NotNull(message = "dpi must be not null")
    @NotBlank(message = "dpi must be not blank")
    private String dpi;
    @NotNull(message = "size must be not null")
    @NotBlank(message = "size must be not blank")
    private String size;
    @NotNull(message = "connection must be not null")
    private boolean connection;
    @NotNull(message = "charger must be not null")
    private boolean charger;
    @NotNull(message = "rbg must be not null")
    private boolean rbg;
    @NotNull(message = "color must be not null")
    @NotBlank(message = "color must be not blank")
    private String color;

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }

    public boolean isCharger() {
        return charger;
    }

    public void setCharger(boolean charger) {
        this.charger = charger;
    }

    public boolean isRbg() {
        return rbg;
    }

    public void setRbg(boolean rbg) {
        this.rbg = rbg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
