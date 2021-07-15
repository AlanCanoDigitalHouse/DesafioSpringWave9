package com.mercadolibre.desafio_spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotBlank(message = "No en blanco")
    @NotNull(message = "Id no puede estar null")
    Integer product_id;

    @NotBlank(message = "El nombre esta vacio")
    @NotNull(message = "El nombre esta nulo")
    String productName;

    String type;
    String brand;
    String color;
    String notes;
}
