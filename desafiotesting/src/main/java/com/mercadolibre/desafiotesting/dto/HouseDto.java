package com.mercadolibre.desafiotesting.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class HouseDto {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @NotNull
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    private String prop_name;
    @NotBlank(message = "El barrio no puede estar vacío.")
    @NotNull
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;
    @Valid
    @NotEmpty
    private List<RoomDto> rooms;
}
