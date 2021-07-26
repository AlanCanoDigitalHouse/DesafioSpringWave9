package com.mercadolibre.calculadorametroscuadrados.dto.response;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import lombok.Data;

@Data
public class PropertySquareFeetDTO {
    private HouseDTO property;
    private Double squareFeet;
}
