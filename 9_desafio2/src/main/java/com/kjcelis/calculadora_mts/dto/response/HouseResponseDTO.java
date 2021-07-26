package com.kjcelis.calculadora_mts.dto.response;

import com.kjcelis.calculadora_mts.dto.DistrictDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseResponseDTO {
    private String prop_name;
    private DistrictDTO district;
    private double square_meter;
    private double property_value;
    private String larger_environment;
    private Map<String, Double> meters_x_environment;

}
