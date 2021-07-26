package com.mercadolibre.calculadorametroscuadrados.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomResponseDTO {
    private String roomName;
    private Integer roomSize;
}
