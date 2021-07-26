package com.mercadolibre.calculadorametroscuadrados.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RoomResponseDTO {
    private String Name;
    private Double squareFeet;
}
