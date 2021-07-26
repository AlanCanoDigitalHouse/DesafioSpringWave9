package com.example.desafiotesting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@AllArgsConstructor
@Getter
public class PropertyDTO {

    @NotNull
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    String prop_name;

    @NotNull
    @Valid
    DistrictDTO district;

    @NotEmpty
    @Valid
    List<EnvironmentDTO> environments;
}
