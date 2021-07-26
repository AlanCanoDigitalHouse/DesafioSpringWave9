package com.example.casitas.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HouseDetailResponseDTO {

    private String name;
    private Double price;

    @JsonProperty("district_name")
    private String districtName;

    @JsonProperty("district_price")
    private Double districtPrice;

    private List<EnvironmentResponseDTO> environment;
}
