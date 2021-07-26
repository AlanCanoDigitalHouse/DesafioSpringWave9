package com.desafios.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentDTO {
    @NotEmpty(message = "El nombre del ambiente no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp = "(\\s*)[A-Z|Á|Ó|É|Í|Ú|Ñ| ][a-z|ñ|ó|í|á|é|ú| |A-Z|Á|Ó|É|Í|Ú|Ñ|0-9]+", message = "El nombre del ambiente debe comenzar con mayúscula.")
    private String environment_name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMin(value = "0.50", message = "El mínimo ancho permitido por propiedad es de 0.50 mts.")
    @DecimalMax(value = "25", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    private Double environment_width;

    @NotNull(message = "El largo de la habitación no puede estar vacío.")
    @DecimalMin(value = "0.50", message = "El mínimo largo permitido por propiedad es de 0.50 mts.")
    @DecimalMax(value = "33", message = "El máximo largo permitido por propiedad es de 33 mts.")
    private Double environment_length;

    public Double getSquareFeet() {
        double result = 0.0;
        if(this.environment_width != null && this.environment_length != null)
            result = this.environment_width * this.environment_length;
        return result;
    }
}
