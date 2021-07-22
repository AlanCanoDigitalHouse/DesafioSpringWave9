package com.mercadolibre.tucasitatasaciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnvironmentDTO {

    @JsonProperty(value = "environment_name")
    private String name;

    @JsonProperty(value = "environment_width")
    private Double width;

    @JsonProperty(value = "environment_length")
    private Double length;

}
