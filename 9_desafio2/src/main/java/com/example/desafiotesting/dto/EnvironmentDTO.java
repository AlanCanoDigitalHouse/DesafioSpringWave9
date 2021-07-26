package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
public class EnvironmentDTO {

    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @NotNull
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    String environment_name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMax(value = "25.0", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    Double environment_width;

    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @DecimalMax(value = "33.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
    Double environment_length;


    public Double calculateSize() {
        return this.getEnvironment_width() * this.getEnvironment_length();
    }
}
