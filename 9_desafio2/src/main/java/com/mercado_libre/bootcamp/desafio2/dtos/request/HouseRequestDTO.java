package com.mercado_libre.bootcamp.desafio2.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercado_libre.bootcamp.desafio2.dtos.DistrictDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.EnvironmentDTO;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class HouseRequestDTO {

    @JsonProperty("prop_name")
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "^[A-Z].*", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "El nombre de la propiedad no puede superar los 30 caracteres.")
    private String name;

    @Valid
    @NotNull(message = "El barrio no puede ser nulo.")
    private DistrictDTO district;

    @NotEmpty(message = "La lista de habitaciones no debe ser vacia.")
    private List<@Valid EnvironmentDTO> environments;
}
