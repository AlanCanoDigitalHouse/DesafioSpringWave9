package com.mercadolibre.socialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Product {
    @NotNull(message = "the productName field must not be null")
    private String productName;
    @NotNull(message = "the type field must not be null")
    private String type;
    private String brand;
    private String color;
    private String notes;
}
