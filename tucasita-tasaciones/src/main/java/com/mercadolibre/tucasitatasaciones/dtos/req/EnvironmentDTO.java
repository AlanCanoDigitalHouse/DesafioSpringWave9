package com.mercadolibre.tucasitatasaciones.dtos.req;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.mercadolibre.tucasitatasaciones.exception.validations.FirstOrderValidation;
import com.mercadolibre.tucasitatasaciones.exception.validations.SecondOrderValidation;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Data
@Validated
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@GroupSequence({EnvironmentDTO.class, FirstOrderValidation.class, SecondOrderValidation.class})
public class EnvironmentDTO {

    @NotBlank(message = "El nombre del ambiente no puede estar vacío.", groups = FirstOrderValidation.class)
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre del ambiente debe comenzar con mayúscula.", groups = SecondOrderValidation.class)
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.", groups = SecondOrderValidation.class)
    private String environmentName;

    @NotNull(message = "El ancho del ambiente no puede estar vacío.")
    @Max(value = 25, message = "El máximo ancho permitido por propiedad es de 25 mts.")
    @Min(value = 1, message = "El mínimo ancho permitido por propiedad es de 1 m.")
    private Double environmentWidth;

    @NotNull(message = "El largo del ambiente no puede estar vacío.")
    @Max(value = 33, message = "El máximo largo permitido por propiedad es de 33 mts.")
    @Min(value = 1, message = "El mínimo largo permitido por propiedad es de 1 m.")
    private Double environmentLength;

    private Double dimension;

}
