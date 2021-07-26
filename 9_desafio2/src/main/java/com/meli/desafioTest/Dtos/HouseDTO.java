package com.meli.desafioTest.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {
    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre debe comenzar con mayúscula.")
    private String prop_name;
    @Valid
    @NotNull(message = "El barrio no puede estar vacio.")
    private DistrictDTO district;
    @NotNull(message = "La casa debe tener al menos una habitacion.")
    private List<@Valid EnvironmentDTO> environments;

}
