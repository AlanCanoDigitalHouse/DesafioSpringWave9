package com.mercado_libre.bootcamp.desafio2.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomResponseDTO {
    private String name;
    private double squaresMeters;
}
