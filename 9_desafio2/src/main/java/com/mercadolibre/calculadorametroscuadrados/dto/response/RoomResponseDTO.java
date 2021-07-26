package com.mercadolibre.calculadorametroscuadrados.dto.response;

import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import lombok.Data;

@Data
public class RoomResponseDTO {
    private RoomDTO environment;
    private Double square_feet;
}
