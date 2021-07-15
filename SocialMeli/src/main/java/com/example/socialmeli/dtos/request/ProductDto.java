package com.example.socialmeli.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductDto {

    @NotNull(message = "El nombre del producto esta vacio")
    private String productName;

    @NotNull(message = "El tipo de producto esta vacio")
    private String type;

    @NotNull(message = "La maeca del producto esta vacia")
    private String brand;

    @NotNull(message = "El color del producto esta vacio")
    private String color;

    @NotNull(message = "Las notas del producto estan vacias")
    private String notes;

}
