package com.mercado_libre.bootcamp.desafio2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@Validated
public class DistrictDTO {

    @JsonProperty("district_name")
    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String districtName;

    @JsonProperty("district_price")
    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMin(value = "0.1", message = "El valor minimo de una propiedad debe ser 0.1")
    @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double districtPrice;
}
