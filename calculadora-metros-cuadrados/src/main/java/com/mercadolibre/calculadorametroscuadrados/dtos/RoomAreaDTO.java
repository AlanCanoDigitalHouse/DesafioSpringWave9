package com.mercadolibre.calculadorametroscuadrados.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomAreaDTO {
    private String environment_name;
    private Double environment_area;
}
