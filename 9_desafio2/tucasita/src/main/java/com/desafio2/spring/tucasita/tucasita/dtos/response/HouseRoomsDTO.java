package com.desafio2.spring.tucasita.tucasita.dtos.response;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class HouseRoomsDTO {
    private Map<String, Double> rooms;
}
