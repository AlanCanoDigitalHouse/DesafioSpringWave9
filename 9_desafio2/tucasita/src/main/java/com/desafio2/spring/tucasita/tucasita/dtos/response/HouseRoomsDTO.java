package com.desafio2.spring.tucasita.tucasita.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
public class HouseRoomsDTO {
    private Map<String, Double> rooms;
}
