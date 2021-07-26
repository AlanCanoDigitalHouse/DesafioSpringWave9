package com.mercado_libre.bootcamp.desafio2.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class House {
    private double squaresMeters;
    private double valuePerSquaresMeters;
    private Room largestRoomResponseDTO;
    private List<Room> roomResponseDTOS;
}
