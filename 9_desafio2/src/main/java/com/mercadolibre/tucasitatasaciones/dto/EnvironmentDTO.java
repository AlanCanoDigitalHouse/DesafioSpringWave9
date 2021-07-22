package com.mercadolibre.tucasitatasaciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvironmentDTO {

    @JsonProperty(value = "environment_name")
    private String name;

    @JsonProperty(value = "environment_width")
    private Double width;

    @JsonProperty(value = "environment_length")
    private Double length;

}
