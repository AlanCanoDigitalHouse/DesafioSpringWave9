package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validations.FirstValidation;
import com.bootcamp.desafio2.validations.SecondValidation;
import com.bootcamp.desafio2.validations.ThirdValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Data
@Validated
@AllArgsConstructor
@GroupSequence({EnvironmentDto.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class EnvironmentDto {

    @NotBlank(message = "El nombre del ambiente no puede ser vacío", groups = FirstValidation.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30, groups = SecondValidation.class)
    @Pattern(regexp="[A-Z][a-z-áéíóú]+ ?(([A-Z]?[a-z-áéíóú]+)+ ?)+",
            message = "El nombre del ambiente debe comenzar con mayúscula.", groups = ThirdValidation.class)
    private String environmentName;

    @NotNull(message = "El ancho del ambiente no puede estar vacío")
    @DecimalMin(message = "El ancho no puede ser menor a 0", value = "0.0")
    @DecimalMax(message = "El máximo ancho permitido por propiedad es de 25mts", value = "25.0")
    private Double environmentWidth;

    @NotNull(message = "El largo del ambiente no puede estar vacío")
    @DecimalMin(message = "El ancho no puede ser menor a 0", value = "0.0")
    @DecimalMax(message = "El máximo largo permitido por propiedad es de 33mts", value = "33.0")
    private Double environmentLength;

    private Double squareMeters;

}
