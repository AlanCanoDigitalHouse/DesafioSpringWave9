package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validations.FirstValidation;
import com.bootcamp.desafio2.validations.SecondValidation;
import com.bootcamp.desafio2.validations.ThirdValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
@GroupSequence({EnvironmentDto.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class EnvironmentDto {

    @NotBlank(message = "El nombre del ambiente no puede ser vacío", groups = FirstValidation.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30, groups = ThirdValidation.class)
    @Pattern(regexp="([A-Z])[\\s|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre del ambiente debe comenzar con mayúscula.", groups = SecondValidation.class)
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
