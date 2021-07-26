package com.mercadolibre.tucasita.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class District {

    @JsonProperty("district_name")
    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(min = 1, max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String name;

    @JsonProperty("district_price")
    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private double squareMeterPrice;
}
