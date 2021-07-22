package com.mercadolibre.tucasitatasaciones.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTotalAreaDTO {

    @JsonProperty(value = "prop_name")
    private String name;

    @JsonProperty(value = "prop_total_area")
    private Double area;

}
