package com.example.casitas.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnvironmentResponseDTO {

    private String name;
    private Double squareMeters;
    private Double price;
}