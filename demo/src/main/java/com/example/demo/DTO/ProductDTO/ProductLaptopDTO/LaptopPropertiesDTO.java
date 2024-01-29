package com.example.demo.DTO.ProductDTO.ProductLaptopDTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LaptopPropertiesDTO {
    @NotNull(message = "cpu must be not null")
    @NotBlank(message = "cpu must be not blank")
    private String cpu;
    @NotNull(message = "vga must be not null")
    @NotBlank(message = "vga must be not blank")
    private String vga;
    @NotNull(message = "ram must be not null")
    @NotBlank(message = "ram must be not blank")
    private String ram;
    @NotNull(message = "ssd must be not null")
    @NotBlank(message = "ssd must be not blank")
    private String ssd;
    @NotNull(message = "screen must be not null")
    @NotBlank(message = "screen must be not blank")
    private String screen;
    @NotNull(message = "size must be not null")
    @NotBlank(message = "size must be not blank")
    private String size;
    @NotNull(message = "operatingSystem must be not null")
    @NotBlank(message = "operatingSystem must be not blank")
    private String operatingSystem;
    @NotNull(message = "color must be not null")
    @NotBlank(message = "color must be not blank")
    private String color;

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
