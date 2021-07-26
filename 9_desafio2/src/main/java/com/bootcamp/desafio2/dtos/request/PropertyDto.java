package com.bootcamp.desafio2.dtos.request;

import com.bootcamp.desafio2.validations.IFirstValidation;
import com.bootcamp.desafio2.validations.ISecondValidation;
import com.bootcamp.desafio2.validations.IThirdValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
@AllArgsConstructor
@NoArgsConstructor
@GroupSequence({PropertyDto.class, IFirstValidation.class, ISecondValidation.class, IThirdValidation.class})
public class PropertyDto {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío", groups = IFirstValidation.class)
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30, groups = ISecondValidation.class)
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.", groups = IThirdValidation.class)
    private String prop_name;

    @NotNull(message = "Debe enviar el barrio donde esta ubicada la casa", groups = IFirstValidation.class)
    @Valid()
    private DistrictDto district;

    @NotEmpty(message = "La casa debe tener al menos una habitacion", groups = IFirstValidation.class)
    @Valid
    private List<EnvironmentDto> environments;


}
