package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validation.FirstValidation;
import com.bootcamp.desafio2.validation.SecondValidation;
import com.bootcamp.desafio2.validation.ThirdValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@Validated
@GroupSequence({DistrictDTO.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class DistrictDTO {

    @NotNull(message = "El barrio no puede estar vacio.", groups = FirstValidation.class)
    @NotBlank(message = "El barrio no puede estar vacio.", groups = FirstValidation.class)
    @Length(message = "La longitud del barrio no puede superar los 45 caracteres.", min=1, max=45, groups = SecondValidation.class)
    private String district_name;

    @NotNull(message = "El precio de un barrio no puede estar vacio.", groups = FirstValidation.class)
    @Max(message="El precio maximo permitido por metro cuadrado no puede superar los 4000 U$S.", value = 4000)
    private double district_price;

}
