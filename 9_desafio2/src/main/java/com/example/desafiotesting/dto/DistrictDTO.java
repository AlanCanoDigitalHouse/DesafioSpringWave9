package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class DistrictDTO {

    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    @NotBlank(message = "El barrio no puede estar vacío.")
    @NotNull
    String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    Double district_price;
}
