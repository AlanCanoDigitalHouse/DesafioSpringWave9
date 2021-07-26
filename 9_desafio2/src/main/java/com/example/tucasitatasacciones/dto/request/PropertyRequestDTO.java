package com.example.tucasitatasacciones.dto.request;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Builder
public class PropertyRequestDTO {

    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @NotEmpty
    @Pattern(regexp = "^[A-Z].*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;

    @NotNull(message = "El distrito no puede estar vacio")
    @Valid
    private DistrictRequestDTO district;

    @NotNull(message = "El ambiente no puede estar vacio")
    @NotEmpty()
    @Valid
    private List<PropertyEnvironmentRequestDTO> environments;
}
