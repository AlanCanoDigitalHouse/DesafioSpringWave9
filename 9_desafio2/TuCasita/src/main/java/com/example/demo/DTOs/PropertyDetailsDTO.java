package com.example.demo.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDetailsDTO {

    private String name;
    private double totalSquareMeters;
    private double totalPrice;
    private EnvironmentResponseDTO biggestEnvironment;
    private List<EnvironmentResponseDTO> environmentsSqrMeters;


}
