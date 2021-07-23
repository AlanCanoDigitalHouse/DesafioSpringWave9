package com.mercadolibre.tucasitatasaciones.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEnvironmentsAreaDTO {

    @JsonProperty(value = "prop_name")
    private String name;

    @JsonProperty(value = "property_environments")
    private List<EnvironmentAreaDTO> environments;

}
