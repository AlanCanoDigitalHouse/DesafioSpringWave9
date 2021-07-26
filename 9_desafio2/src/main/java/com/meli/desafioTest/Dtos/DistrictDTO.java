package com.meli.desafioTest.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class DistrictDTO {
    @NotNull(message = "El nombre del barrio no puede estar vacío.")
    @NotEmpty(message = "El nombre del barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;
    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMax(value = "4000.0", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    @DecimalMin(value = "100.0", message = "El precio minimo permitido por metro cuadrado no puede ser inferior a los 100 U$S.")
    private Double district_price;
}
