package com.mercadolibre.tucasitatasaciones.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.tucasitatasaciones.dto.EnvironmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LargestEnvironmentDTO {

    @JsonProperty(value = "prop_name")
    private String name;

    @JsonProperty(value = "prop_largest_environment")
    private EnvironmentDTO largestEnvironment;

}

