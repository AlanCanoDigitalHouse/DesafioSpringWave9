package com.mercadolibre.tucasitatasaciones.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.tucasitatasaciones.dto.EnvironmentDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LargestEnvironmentDTO {

    @JsonProperty(value = "prop_name")
    private String name;

    @JsonProperty(value = "prop_largest_environment")
    private EnvironmentAreaDTO largestEnvironment;

}

