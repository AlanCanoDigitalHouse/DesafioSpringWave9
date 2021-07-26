package com.mercadolibre.squaremeter.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Environment {
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @NotNull(message = "El nombre del ambiente no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre del ambiente debe comenzar con mayúscula.")
    private String environment_name;
    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts")
    @Min(value = 1, message = "El minimo ancho permitido por propiedad es de 1 mts")
    private Double environment_width;
    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mt")
    @Min(value = 1, message = "El minimo largo permitido por propiedad es de 1 mts")
    private Double environment_length;
}
