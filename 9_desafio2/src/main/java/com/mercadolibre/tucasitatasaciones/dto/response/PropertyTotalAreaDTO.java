package com.mercadolibre.tucasitatasaciones.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyTotalAreaDTO {

    @JsonProperty(value = "prop_name")
    private String name;

    @JsonProperty(value = "prop_total_area")
    private Double area;

}
