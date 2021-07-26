package com.mercadolibre.calculadorametroscuadrados.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class RoomResponseBiggestDTO {

    private String environment_name;
    private Double environment_area;

    public RoomResponseBiggestDTO(String biggest_name, Double biggest_size) {
        this.environment_area = biggest_size;
        this.environment_name = biggest_name;
    }
}
