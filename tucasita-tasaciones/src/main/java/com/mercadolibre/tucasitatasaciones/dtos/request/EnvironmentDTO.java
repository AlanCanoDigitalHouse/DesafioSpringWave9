package com.mercadolibre.tucasitatasaciones.dtos.request;

import com.mercadolibre.tucasitatasaciones.validations.FirstValidation;
import com.mercadolibre.tucasitatasaciones.validations.SecondValidation;
import com.mercadolibre.tucasitatasaciones.validations.ThirdValidation;
import lombok.*;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@GroupSequence({EnvironmentDTO.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class EnvironmentDTO {

    @NotBlank(message = "El nombre del ambiente no puede ser vacío", groups = FirstValidation.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30, groups = SecondValidation.class)
    @Pattern(regexp="([A-Z]).+", message = "El nombre del ambiente debe comenzar con mayúscula.", groups = ThirdValidation.class)
    private String environment_name;

    @NotNull(message = "El ancho del ambiente no puede estar vacío", groups = FirstValidation.class)
    @DecimalMin(message = "El ancho no puede ser menor a 0", value = "0.0", groups = SecondValidation.class)
    @DecimalMax(message = "El máximo ancho permitido por propiedad es de 25mts", value = "25.0", groups = ThirdValidation.class)
    private Double environment_width;

    @NotNull(message = "El largo del ambiente no puede estar vacío", groups = FirstValidation.class)
    @DecimalMin(message = "El ancho no puede ser menor a 0", value = "0.0", groups = SecondValidation.class)
    @DecimalMax(message = "El máximo largo permitido por propiedad es de 33mts", value = "33.0", groups = ThirdValidation.class)
    private Double environment_length;

    private Double squareMeters;

}