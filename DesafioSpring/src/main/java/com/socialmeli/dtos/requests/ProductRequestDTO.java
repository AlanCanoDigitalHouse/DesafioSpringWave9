package com.socialmeli.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
public class ProductRequestDTO {

    @NotNull(message = "productName must not be null")
    @NotBlank(message = "productName must not be blank")
    private String productName;

    @NotNull(message = "type must not be null")
    @NotBlank(message = "type must not be blank")
    private String type;

    @NotNull(message = "brand must not be null")
    @NotBlank(message = "brand must not be blank")
    private String brand;

    @NotNull(message = "color must not be null")
    @NotBlank(message = "color must not be blank")
    private String color;

    @NotNull(message = "notes must not be null")
    @NotBlank(message = "notes must not be blank")
    private String notes;

}
