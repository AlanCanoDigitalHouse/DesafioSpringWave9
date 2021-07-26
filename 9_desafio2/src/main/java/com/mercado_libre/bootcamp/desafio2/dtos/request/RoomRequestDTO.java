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
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String environment_name;

    @NotBlank(message = "El ancho del ambiente no puede estar vacío.")
    @NotNull(message = "El ancho del ambiente no puede ser nulo")
    @DecimalMin(value = "0.1", message = "El mínimo ancho permitido por propiedad es de al menos 0.1 mts.")
    @DecimalMax(value = "25", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    private double environment_width;

    @NotBlank(message = "El largo del ambiente no puede estar vacío.")
    @NotNull(message = "El largo del ambiente no puede ser nulo")
    @DecimalMin(value = "0.1", message = "El mínimo largo permitido por propiedad es de al menos 0.1 mts.")
    @DecimalMax(value = "33", message = "El máximo largo permitido por propiedad es de 33 mts.")
    private double environment_length;

    public double getSquareMeters() {
        return environment_width * environment_length;
    }

}
