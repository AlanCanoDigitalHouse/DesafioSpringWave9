package com.example.tucasitatasacciones.dto.request.property;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
public class PropertyEnvironmentRequestDTO {

    @NotNull(message = "El nombre del ambiente no puede estar vacío.")
    @NotEmpty(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp = "^[A-Z].*$", message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String environment_name;

    @DecimalMax(value = "33", message = "El máximo largo permitido por propiedad es de 33 mts.")
    @NotNull(message = "El largo del ambiente no puede estar vacio")
    private Double environment_length;

    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMax(value = "25", message = "El máximo ancho permitido por propiedad es de 25 mts")
    private Double environment_width;

}
