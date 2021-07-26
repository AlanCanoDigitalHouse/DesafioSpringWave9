package com.meli.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
@Getter
@AllArgsConstructor
public class EnvironmentDTO {

    @NotNull(message = "El nombre del ambiente no puede estar vacío")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre del ambiente debe comenzar con mayúscula")
    @Length(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    private String environment_name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío")
    @DecimalMax(value = "25.0", message = "El máximo ancho permitido por ambiente es de 25 mts ")
    @DecimalMin(value = "1.0", message = "El minimo ancho permitido debe ser superior a 0 mts ")
    private Double environment_width;

    @NotNull(message = "El largo del ambiente no puede estar vacío")
    @DecimalMax(value = "33.0", message = "El máximo largo permitido por ambiente es de 33 mts ")
    @DecimalMin(value = "1.0", message = "El minimo largo permitido debe ser superior a 0 mts ")
    private Double environment_length;
}
