package com.mercadolibre.tucasitatasaciones.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dto.EnvironmentDTO;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequestDTO {

    @NotEmpty(message = "El nombre de la propiedad no puede estar vacío.")
    @Pattern(regexp="([A-Z]|[0-9])[\\s|[0-9]|A-Z|a-z|ñ|ó|í|á|é|ú|Á|Ó|É|Í|Ú]*$",
            message = "El nombre de la propiedad debe comenzar con mayúscula.")
    @Size(max = 30, message = "La longitud del nombre no puede superar los 30 caracteres.")
    @JsonProperty(value = "prop_name")
    private String name;

    @Valid
    @JsonProperty(value = "prop_district")
    private DistrictDTO district;

    @Valid
    @NotEmpty(message = "La lista de ambientes no puede estar vacía.")
    @JsonProperty(value = "prop_environments")
    private List<EnvironmentDTO> environments;

}
