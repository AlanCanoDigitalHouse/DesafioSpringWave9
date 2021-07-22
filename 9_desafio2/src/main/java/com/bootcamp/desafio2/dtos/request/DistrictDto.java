package com.bootcamp.desafio2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class DistrictDto {

    @NotBlank(message = "El barrio no puede estar vacío")
    @Size(message = "La longitud del barrio no puede superar los 45 caracteres", max = 45)
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío")
    @DecimalMin(message = "El precio del metro cuadrado no puede ser menor a cero", value = "0.0")
    @DecimalMax(message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S",
            value = "4000.0")
    private Double district_price;

}
