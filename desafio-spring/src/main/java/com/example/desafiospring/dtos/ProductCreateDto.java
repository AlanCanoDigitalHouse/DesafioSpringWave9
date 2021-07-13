package com.example.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
public class ProductCreateDto {

    @NotNull(message = "El nombre del producto es obligatorio")
    @NotBlank(message = "El nombre del producto no puede ser vacio")
    private String productName;

    @NotNull(message = "El tipo del producto es obligatorio")
    @NotBlank(message = "El tipo del producto no puede ser vacio")
    private String type;

    @NotNull(message = "La marca del producto es obligatoria")
    @NotBlank(message = "La marca del producto no puede ser vacia")
    private String brand;

    @NotNull(message = "El color del producto es obligatorio")
    @NotBlank(message = "El color del producto no puede ser vacio")
    private String color;

    @NotNull(message = "El producto debe tener alguna nota")
    @NotBlank(message = "El nota del producto no puede ser vacia")
    private String notes;

}
