package com.bootcamp.desafio2.dtos.response;

import com.bootcamp.desafio2.dtos.request.EnvironmentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HouseResponseDTO {

    private Double totalArea;
    private Double housePrice;
    private EnvironmentDTO biggestRoom;
    private List<EnvironmentDTO> rooms;

}
