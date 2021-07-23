package com.mercadolibre.tucasitatasaciones.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentAreaDTO {

    @JsonProperty(value = "environment_name")
    private String name;

    @JsonProperty(value = "environment_area")
    private Double area;

}
