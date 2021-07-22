package com.bootcamp.desafio2.dtos.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class EnvironmentDto {

    @NotBlank(message = "El nombre del ambiente no puede ser vacío")
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30)
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre del ambiente debe comenzar con mayúscula.")
    private String environment_name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío")
    @DecimalMin(message = "El ancho no puede ser menor a 0", value = "0.0")
    @DecimalMax(message = "El máximo ancho permitido por propiedad es de 25mts", value = "25.0")
    private Double environment_width;

    @NotNull(message = "El largo del ambiente no puede estar vacío")
    @DecimalMin(message = "El ancho no puede ser menor a 0", value = "0.0")
    @DecimalMax(message = "El máximo largo permitido por propiedad es de 33mts", value = "33.0")
    private Double environment_length;

    private Double squareMeters;

}
