package com.mercadolibre.tucasitatasaciones.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mercadolibre.tucasitatasaciones.dto.DistrictDTO;
import com.mercadolibre.tucasitatasaciones.dto.EnvironmentDTO;

import javax.validation.Valid;
import java.util.List;

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
