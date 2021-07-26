package com.mercado_libre.bootcamp.desafio2.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class EnvironmentDTO {

    @JsonProperty("environment_name")
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String name;

    @JsonProperty("environment_width")
    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMin(value = "0.1", message = "El valor debe ser mayor a 0.1")
    @DecimalMax(value = "25.0", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    private Double width;

    @JsonProperty("environment_length")
    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @DecimalMin(value = "0.1", message = "El valor debe ser mayor a 0.1")
    @DecimalMax(value = "33.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
    private Double length;

    public double getSquareMeters() {
        return width * length;
    }
}
