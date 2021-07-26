package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validation.FirstValidation;
import com.bootcamp.desafio2.validation.SecondValidation;
import com.bootcamp.desafio2.validation.ThirdValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.constraints.*;

@Validated
@Data
@AllArgsConstructor
@NoArgsConstructor
@GroupSequence({EnvironmentDTO.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class EnvironmentDTO {

    @NotNull(message = "El nombre del ambiente no puede estar vacio.", groups = FirstValidation.class)
    @NotBlank(message = "El nombre del ambiente no puede estar vacio.", groups = FirstValidation.class)
    @Length(message = "La longitud del nombre no puede superar los 30 caracteres.", min=1, max=30, groups = SecondValidation.class)
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre del ambiente debe comenzar con mayuscula.", groups = ThirdValidation.class)
    private String environment_name;

    @NotNull(message = "El largo del ambiente no puede estar vacio.", groups = FirstValidation.class)
    @Min(message = "El largo no puede ser menor a 0", value = 0)
    @Max(message = "El maximo largo permitido por propiedad es de 33 mts.", value = 33)
    private Double environment_length;

    @NotNull(message = "El ancho del ambiente no puede estar vacio.", groups = FirstValidation.class)
    @Min(message = "El ancho no puede ser menor a 0", value = 0)
    @Max(message = "El maximo ancho permitido por propiedad es de 25 mts.", value = 25)
    private Double environment_width;


    private Double squareMeters;

}
