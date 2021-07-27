package com.mercadolibre.tucasita.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@Getter
@Setter
@AllArgsConstructor
public class PropertyDTO {

    @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;

    @Valid
    @NotNull(message = "Por favor proporcione un barrio para la propiedad")
    private DistrictDTO district;

    @NotNull(message = "La propiedad debe tener ambientes.")
    private List<EnvironmentDTO> environments;


}
