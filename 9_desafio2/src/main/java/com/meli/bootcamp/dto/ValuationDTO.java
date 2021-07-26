package com.meli.bootcamp.dto;


import com.meli.bootcamp.model.Property;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ValuationDTO {
    private String property_name;
    private Double property_area;
    private Double property_valuation;
    private EnvironmentDetailsDTO biggest_environment;
    private List<EnvironmentDetailsDTO> environmentDetails;

    public ValuationDTO(Property property){
        this.property_name = property.getProperty_name();
        this.property_area = property.getArea_property();
        this.property_valuation=property.getValuation_property();
        this.environmentDetails=property.getEnvironments().stream().map(EnvironmentDetailsDTO::new).collect(Collectors.toList());
    }

}

