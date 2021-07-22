package com.example.desafio2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseResponseDTO {

    private String name;
    private Double squareMeters;
}
