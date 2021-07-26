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
    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^\\p{Lu}.*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    String prop_name;

    @NotNull
    @Valid
    DistrictDTO district;

    @NotEmpty
    @Valid
    List<EnvironmentDTO> environments;
}
