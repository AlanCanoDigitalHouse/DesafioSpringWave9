package com.example.desafio2.dtos;

import lombok.AllArgsConstructor;

import lombok.Getter;


import java.util.List;

@Getter
@AllArgsConstructor
public class HouseDetailResponseDto {
    private String name;
    private double price;
    private String district_name;
    private double district_price;
    private List<EnvResponseDto> environment;
}
