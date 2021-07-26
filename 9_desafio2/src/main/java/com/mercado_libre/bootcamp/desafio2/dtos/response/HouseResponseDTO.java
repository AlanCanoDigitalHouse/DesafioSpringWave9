package com.mercado_libre.bootcamp.desafio2.dtos.response;

import com.mercado_libre.bootcamp.desafio2.dtos.request.RoomRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class HouseResponseDTO {
    private double squaresMeters;
    private double valuePerSquaresMeters;
    private RoomRequestDTO largestRoom;
    private List<RoomResponseDTO> environments;
}
