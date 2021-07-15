package com.mercadolibre.socialmeli.models;

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
    @NotNull(message = "productName Required")
    private String productName;
    @NotNull(message = "type Required")
    private String type;
    private String brand;
    private String color;
    private String notes;
}
