package com.example.desafio2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnvResponseDto {
    private String name;
    private double squareMeters;
    private double price;
}
