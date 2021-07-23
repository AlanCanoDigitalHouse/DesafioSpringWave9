package com.example.tucasitatasaciones.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DistrictDTO {

    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(message = "La longitud del barrio no puede superar los 45 caracteres.", max = 45)
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMax(message = "El precio máximo permitido por metro cuadrado no puede superar los 4000U$S.", value = "4000")
    @DecimalMin(message = "El precio minimo permitido por metro cuadrado debe superar los 1U$S.", value = "1")
    private Double district_price;
}
