package com.mercado_libre.bootcamp.desafio2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
public class RoomRequestDTO {
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @NotNull(message = "El nombre del ambiente no puede ser nulo.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del ambiente debe comenzar con mayuscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String environment_name;

    @DecimalMin(value = "0.1", message = "El valor debe ser mayor a 0.1mts.")
    @DecimalMax(value = "25.0", message = "El maximo ancho permitido por propiedad es de 25 mts.")
    private Double environment_width;

    @DecimalMin(value = "0.1", message = "El valor debe ser mayor a 0.1mts.")
    @DecimalMax(value = "33.0", message = "El maximo largo permitido por propiedad es de 33 mts.")
    private Double environment_length;

    public double getSquareMeters() {
        return environment_width * environment_length;
    }

}
