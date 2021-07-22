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
public class DistrictDTO {

    @JsonProperty(value = "district_name")
    private String name;

    @JsonProperty(value = "district_price")
    private Double price;

}
