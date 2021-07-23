package com.desafio.tucasitatasaciones.dto;

import com.desafio.tucasitatasaciones.model.Environment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropertyRequestDTO {
    @NotBlank(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp = "(([A-Z][a-z-áéíóúñ]+)+ ?)+", message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    private String prop_name;
    @NotBlank(message = "El barrio no puede estar vacío.")
    @Size(max = 45, message = "La longitud del barrio no puede superar los 45 caracteres.")
    private String district_name;
    @Positive(message = "El precio de un barrio no puede estar vacío.")
    @DecimalMax(value = "4000", message = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.")
    private double district_price;
    private List<@Valid Environment> environments;

    public PropertyRequestDTO(PropertyRequestDTO propertyRequestDTO){
        this.prop_name = propertyRequestDTO.getProp_name();
        this.district_name = propertyRequestDTO.getDistrict_name();
        this.district_price = propertyRequestDTO.getDistrict_price();
        this.environments = propertyRequestDTO.getEnvironments();
    }
}
