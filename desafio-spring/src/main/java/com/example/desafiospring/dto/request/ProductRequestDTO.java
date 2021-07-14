package com.example.desafiospring.dto.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class ProductRequestDTO {
    @NotNull(message = "productName is null")
    @NotBlank(message = "productName is empty")
    private String productName;
    @NotNull(message = "type is null")
    private String type;
    @NotNull(message = "brand is null")
    @NotBlank(message = "brand is empty")
    private String brand;
    @NotNull(message = "color is null")
    @NotBlank(message = "color is empty")
    private String color;
    private String notes;

    public ProductRequestDTO(String productName, String type, String brand, String color, String notes) {
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
    }

    public ProductRequestDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
