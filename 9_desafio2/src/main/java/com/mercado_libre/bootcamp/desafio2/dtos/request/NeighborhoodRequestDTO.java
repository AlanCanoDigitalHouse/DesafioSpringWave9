package com.mercado_libre.bootcamp.desafio2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Getter
@Setter
@Validated
@AllArgsConstructor
public class NeighborhoodRequestDTO {
    @NotBlank(message = "El barrio no puede estar vacío.")
    @NotNull(message = "El barrio no puede ser nulo.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del districto debe comenzar con mayuscula.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede ser nulo.")
    @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double district_price;
}
