package com.mercado_libre.bootcamp.desafio2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Validated
@AllArgsConstructor
public class NeighborhoodRequestDTO {
    @NotBlank(message = "El barrio no puede estar vacío.")
    @NotNull(message = "El barrio no puede ser nulo.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;

    @NotBlank(message = "El precio de un barrio no puede estar vacío.")
    @NotNull(message = "El precio de un barrio no puede ser nulo.")
    @Size(max = 4000, message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double district_price;
}
