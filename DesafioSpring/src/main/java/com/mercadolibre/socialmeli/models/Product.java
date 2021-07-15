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
    @NotNull(message = "El nombre del producto debe estar")
    private String productName;
    @NotNull(message = "El tipo de producto debe estar")
    private String type;
    @NotNull(message = "La marca debe estar")
    private String brand;
    @NotNull(message = "El color debe estar")
    private String color;
    @NotNull(message = "Debe agregar una nota")
    private String notes;
}
