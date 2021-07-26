package com.mercadolibre.tucasita.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Room {

    @JsonProperty("environment_name")
    @NotBlank(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp = "^[A-Z]\\w*", message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(min = 1, max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String name;

    @JsonProperty("environment_width")
    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts.")
    private double width;

    @JsonProperty("environment_length")
    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mts.")
    private double length;
}
