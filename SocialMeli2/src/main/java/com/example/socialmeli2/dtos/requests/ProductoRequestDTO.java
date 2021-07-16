package com.example.socialmeli2.dtos.requests;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ProductoRequestDTO {
    @NotNull(message = "El nombre del producto no puede ser nulo")
    private String productName;
    @NotNull(message = "La descripcion no puede ser nulo")
    private String brand;
    @NotNull(message = "El color del producto no puede ser nulo")
    private String  color;
    @NotNull(message = "Las notas del producto no puede ser nulo")
    private String notes;
}



