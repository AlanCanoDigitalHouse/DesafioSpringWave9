package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocationDTO {

    @NotNull(message = "El barrio no puede estar vacio.")
    @NotEmpty(message = "El barrio no puede estar vacio.")
    @Size(max=45, message = "La longitud del barrio no debe superar los 45 caracteres.")
    private String location;

    @NotNull(message = "El precio de un barrio no puede estar vacio.")
    @Max(value = 4000, message = "El precio máximo permitido por metro cuadrado no debe superar los 4000")
    private Integer price;

}
