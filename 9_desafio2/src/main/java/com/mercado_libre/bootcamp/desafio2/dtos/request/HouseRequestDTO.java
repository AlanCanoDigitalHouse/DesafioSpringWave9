package com.mercado_libre.bootcamp.desafio2.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@Validated
@AllArgsConstructor
public class HouseRequestDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @NotNull(message = "El nombre de la propiedad no puede ser nulo.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayuscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;

    @Valid private NeighborhoodRequestDTO district;

    @NotEmpty(message = "Debe tener al menos una habitación")
    private List<@Valid RoomRequestDTO> environments;
}
