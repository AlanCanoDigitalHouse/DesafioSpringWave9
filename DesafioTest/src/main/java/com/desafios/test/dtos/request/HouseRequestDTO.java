package com.desafios.test.dtos.request;

import com.desafios.test.dtos.DistrictDTO;
import com.desafios.test.dtos.EnvironmentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@AllArgsConstructor
public class HouseRequestDTO {
    @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracters.")
    @Pattern(regexp = "(\\s*)[A-Z|Á|Ó|É|Í|Ú|Ñ| ][a-z|ñ|ó|í|á|é|ú| |A-Z|Á|Ó|É|Í|Ú|0-9]+", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    private String prop_name;

    @NotNull(message = "El distrito no puede ser nulo.")
    @Valid
    private DistrictDTO district;

    @NotNull(message = "La lista de habitaciones no puede ser nula.")
    @NotEmpty(message = "La lista de habitaciones no puede estar vacía.")
    @Valid
    private List<EnvironmentDTO> environments;
}
