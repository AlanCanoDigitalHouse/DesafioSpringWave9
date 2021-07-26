package com.example.tucasitatasacciones.dto.request.property;

import com.example.tucasitatasacciones.dto.request.district.DistrictRequestDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @NotEmpty(message = "El ambiente no puede estar vacio")
    @Valid
    private List<PropertyEnvironmentRequestDTO> environments;
}
