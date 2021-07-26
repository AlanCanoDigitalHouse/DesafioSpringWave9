package com.desafios.test.dtos.response;

import com.desafios.test.dtos.DistrictDTO;
import com.desafios.test.dtos.EnvironmentDTO;
import com.desafios.test.dtos.request.HouseRequestDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HouseResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String prop_name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private DistrictDTO district;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<EnvironmentDTO> environments;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double squareFeet;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Double price;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private EnvironmentDTO biggest;

    public HouseResponseDTO(HouseRequestDTO house) {
        this.setProp_name(house.getProp_name());
        this.setDistrict(house.getDistrict());
        this.setEnvironments(house.getEnvironments());
    }
}