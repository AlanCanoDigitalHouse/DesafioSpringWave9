package com.mercadolibre.socialmeli.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class Product {
    int product_id;
    @NotNull(message = "Product name cannot be null")
    @NotBlank(message = "Product name cannot be blank")
    String productName;
    @NotNull(message = "Type name cannot be null")
    @NotBlank(message = "Type name cannot be blank")
    String type;
    @NotNull(message = "Brand name cannot be null")
    @NotBlank(message = "Brand name cannot be blank")
    String brand;
    @NotNull(message = "Color name cannot be null")
    @NotBlank(message = "Color name cannot be blank")
    String color;
    @NotNull(message = "Notes name cannot be null")
    @NotBlank(message = "Notes name cannot be blank")
    String notes;
}
