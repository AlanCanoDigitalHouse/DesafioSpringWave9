package com.bootcamp.desafio2.dtos.request;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class PropertyDto {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío")
    @Size(message = "La longitud del nombre no puede superar los 30 caracteres", max = 30)
    @Pattern(regexp="([A-Z])[\\s|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre de la propiedad debe comenzar con mayúscula.")
    private String prop_name;

    @NotNull(message = "Debe enviar el barrio donde esta ubicada la casa")
    @Valid
    private DistrictDto district;

    @NotEmpty(message = "La casa debe tener almenos una habitacion")
    @Valid
    private List<EnvironmentDto> environments;


}
