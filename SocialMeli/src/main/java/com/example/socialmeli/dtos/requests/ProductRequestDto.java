package com.example.socialmeli.dtos.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Validated
public class ProductRequestDto {
    @NotBlank(message = "productName en blanco")
    @NotEmpty(message = "productName vacío")
    private String productName;

    @NotBlank(message = "type en blanco")
    @NotEmpty(message = "type vacío")
    private String type;

    @NotBlank(message = "brand en blanco")
    @NotEmpty(message = "brand vacío")
    private String brand;

    @NotBlank(message = "color en blanco")
    @NotEmpty(message = "color vacío")
    private String color;

    @NotBlank(message = "notes en blanco")
    @NotEmpty(message = "notes vacío")
    private String notes;
}
