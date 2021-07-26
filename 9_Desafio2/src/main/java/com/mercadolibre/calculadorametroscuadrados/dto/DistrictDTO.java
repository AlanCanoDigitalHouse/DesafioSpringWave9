package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
public class DistrictDTO {

    @NotNull(message = "El barrio no puede estar vacio.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String name;

    @NotNull(message = "El precio de un barrio no puede estar vacio.")
    @Max(value = 4000, message = "El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S")
    private double price;
}
