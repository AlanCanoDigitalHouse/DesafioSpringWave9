package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Validated
public class HouseDTO {
    @NotNull(message = "El nombre de la propiedad no puede estar vacío.")
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;
    @NotNull(message = "El barrio no puede estar vacío.")
    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;
    @NotNull(message = "El precio de un barrio no puede estar vacío.")
    @Min( value = 0,  message = "El precio de un barrio no puede estar vacío.")
    @Max(value = 4000,message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private Double district_price;
    @NotEmpty
    @NotNull
    @Valid
    private List<RoomDTO> environments;
}
