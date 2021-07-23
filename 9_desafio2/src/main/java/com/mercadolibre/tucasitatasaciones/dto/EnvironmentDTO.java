package com.mercadolibre.tucasitatasaciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentDTO {

    @NotEmpty(message = "El nombre del ambiente no puede estar vacío.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre del ambiente debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @JsonProperty(value = "environment_name")
    private String name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @DecimalMax(value = "25.0", message = "El máximo ancho permitido por propiedad es de 25 mts.")
    @JsonProperty(value = "environment_width")
    private Double width;

    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @DecimalMax(value = "33.0", message = "El máximo largo permitido por propiedad es de 33 mts.")
    @JsonProperty(value = "environment_length")
    private Double length;

}
