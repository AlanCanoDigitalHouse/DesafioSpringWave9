package com.mercadolibre.desafio_testing.dto.response;

import lombok.AllArgsConstructor;
import lombok.*;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class RoomsSquareMetersResponseDTO {
    private List<RoomSquareMetersResponseDTO> rooms;
}
