package com.example.desafiospring.DTOS.requests;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Validated
public class ProductRequestDTO {
    @NotNull(message = "productName is mandatory")
    @NotBlank(message = "productName is mandatory")
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
