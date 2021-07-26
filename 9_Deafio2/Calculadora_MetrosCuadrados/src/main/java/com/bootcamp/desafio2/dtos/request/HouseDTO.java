package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validation.FirstValidation;
import com.bootcamp.desafio2.validation.SecondValidation;
import com.bootcamp.desafio2.validation.ThirdValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Validated
@Data
@AllArgsConstructor
@GroupSequence({HouseDTO.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class HouseDTO {

    @NotNull(message = "El nombre de la propiedad no puede estar vacío.", groups = FirstValidation.class)
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.", groups = FirstValidation.class)
    @Length(message = "La longitud del nombre no puede superar los 30 caracteres.", min=1, max=30, groups = SecondValidation.class)
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre de la propiedad debe comenzar con mayúscula.", groups = ThirdValidation.class)
    private String prop_name;

    @Valid
    private DistrictDTO district;


    @NotEmpty(message = "La casa debe tener almenos una habitacion")
    @Valid
    private List<EnvironmentDTO> rooms;


}
