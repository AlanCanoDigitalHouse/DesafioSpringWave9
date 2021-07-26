package com.mercadolibre.calculadorametroscuadrados.dto.response;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import lombok.Data;

@Data
public class PropertyPriceDTO {
    private HouseDTO property;
    private Double price;
}
