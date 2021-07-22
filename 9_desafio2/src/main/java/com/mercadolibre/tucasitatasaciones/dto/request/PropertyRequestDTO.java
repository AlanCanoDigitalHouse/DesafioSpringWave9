package com.mercadolibre.tucasitatasaciones.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dto.EnvironmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyRequestDTO {

    @JsonProperty(value = "prop_name")
    private String name;

    @JsonProperty(value = "prop_district")
    @Valid
    private DistrictDTO district;

    @JsonProperty(value = "prop_environments")
    @Valid
    private List<EnvironmentDTO> environments;

}
