package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validations.FirstValidation;
import com.bootcamp.desafio2.validations.SecondValidation;
import com.bootcamp.desafio2.validations.ThirdValidation;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
@GroupSequence({PropertyDto.class, FirstValidation.class, SecondValidation.class, ThirdValidation.class})
public class PropertyDto {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío", groups = FirstValidation.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30, groups = ThirdValidation.class)
    @Pattern(regexp="([A-Z])[\\s|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre de la propiedad debe comenzar con mayúscula.", groups = SecondValidation.class)
    private String prop_name;

    @NotNull(message = "Debe enviar el barrio donde esta ubicada la casa")
    @Valid
    private DistrictDto district;

    @NotEmpty(message = "La casa debe tener almenos una habitacion")
    @Valid
    private List<EnvironmentDto> environments;


}
