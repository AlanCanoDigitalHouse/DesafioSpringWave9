package com.example.socialmeli.dtos.requests;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RequestProductDto {
    @NotBlank(message = "El nombre no puede estar vacío")
    @NotNull(message = "El nombre no peude estar nulo")
    private String productName;

    @NotBlank(message = "Type es un campo obligatorio")
    @NotNull(message = "Type es un campo obligatorio")
    private String type;

    @NotBlank(message = "Brand es un campo obligatorio")
    @NotNull(message = "Brand es un campo obligatorio")
    private String brand;

    @NotBlank(message = "Color es un campo obligatorio")
    @NotNull(message = "Color es un campo obligatorio")
    private String color;

    private String notes;
}
