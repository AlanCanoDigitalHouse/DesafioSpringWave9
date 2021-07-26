package com.mercadolibre.calculadorametroscuadrados.dto;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.DistrictDTO;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Validated
public class HouseDTO {

    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String name;

    @Valid
    private DistrictDTO district;
}
