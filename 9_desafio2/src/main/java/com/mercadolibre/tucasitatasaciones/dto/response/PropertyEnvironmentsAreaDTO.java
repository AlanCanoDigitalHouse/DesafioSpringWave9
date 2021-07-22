package com.mercadolibre.tucasitatasaciones.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEnvironmentsAreaDTO {

    @JsonProperty(value = "prop_name")
    private String name;

    @JsonProperty(value = "property_environments")
    private List<EnvironmentAreaDTO> environments;

}
