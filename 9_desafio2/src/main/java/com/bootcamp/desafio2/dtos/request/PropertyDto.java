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
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres")
    @Pattern(regexp="[A-Z][a-z-áéíóú]+ ?(([A-Z]?[a-z-áéíóú]+)+ ?)+",
            message = "El nombre de la propiedad debe comenzar con mayúscula.")
    private String propName;

    @NotNull(message = "Debe enviar el barrio donde esta ubicada la casa")
    @Valid
    private DistrictDto district;

    @NotEmpty(message = "La casa debe tener almenos una habitacion")
    @Valid
    private List<EnvironmentDto> environments;


}
