package com.mercadolibre.tucasitatasaciones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DistrictDTO {

    @JsonProperty(value = "district_name")
    private String name;

    @JsonProperty(value = "district_price")
    private Double price;

}
