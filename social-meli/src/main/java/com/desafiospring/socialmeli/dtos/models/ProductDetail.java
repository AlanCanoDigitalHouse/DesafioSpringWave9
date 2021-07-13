package com.desafiospring.socialmeli.dtos.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductDetail {

    @NotNull(message = "Product name no debe ser nulo")
    private String productName;

    @NotNull(message = "Tipo no debe ser nulo")
    private String type;

    @NotNull(message = "Brand no debe ser nulo")
    private String brand;

    @NotNull(message = "Color no debe ser nulo")
    private String color;

    @NotNull(message = "Notes no debe ser nulo")
    private String notes;
}
