package com.kjcelis.calculadora_mts.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.*;

@Data
@Getter
@AllArgsConstructor
public class EnvironmentDTO {
    @NotBlank(message = "El nombre del ambiente no puede estar vacio")
    @Pattern(regexp = "[A-Z](\\p{Alpha}||\\s)+", message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String evironment_name;
    @NotNull(message = "El ancho del ambiente no puede estar vacio.")
    @DecimalMax(value = "25.0", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    @DecimalMin(value = "1.0", message = "El minimo ancho permitido por propiedad es de 1 mts.")
    private double environment_width;
    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @DecimalMax(value = "35.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
    @DecimalMin(value = "1.0", message = "El minimo largo permitido por propiedad es de 1 mts.")
    private double environment_length;


}
