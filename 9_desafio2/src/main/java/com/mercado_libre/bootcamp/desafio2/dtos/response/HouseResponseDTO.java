package com.mercado_libre.bootcamp.desafio2.dtos.response;

import com.mercado_libre.bootcamp.desafio2.dtos.EnvironmentDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HouseResponseDTO {

    private double squaresMeters;
    private double valuePerSquaresMeters;
    private EnvironmentDTO biggestRoom;
    private List<EnvironmentDTO> rooms;
}
