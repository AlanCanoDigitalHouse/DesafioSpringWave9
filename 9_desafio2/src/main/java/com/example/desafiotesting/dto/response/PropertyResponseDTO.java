package com.example.desafiotesting.dto.response;

import com.example.desafiotesting.dto.DistrictDTO;
import com.example.desafiotesting.dto.EnvironmentDTO;
import com.example.desafiotesting.dto.PropertyDTO;
import lombok.Data;

import java.util.List;

@Data
public class PropertyResponseDTO {
    private String prop_name;
    private DistrictDTO district;
    private List<EnvironmentDTO> environments;
    private Double squareMetter;
    private double price;
    private EnvironmentDTO biggest;

    public PropertyResponseDTO(PropertyDTO property) {
        this.setProp_name(property.getProp_name());
        this.setDistrict(property.getDistrict());
        this.setEnvironments(property.getEnvironments());
    }


}
