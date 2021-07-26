package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.RoomDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertyPriceDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.PropertySquareFeetDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.RoomResponseDTO;

import java.util.List;

public interface CalculateService {

    HouseResponseDTO calculate(HouseDTO house);

    PropertySquareFeetDTO calculatePropertySquareFeet(HouseDTO property);

    PropertyPriceDTO calculatePropertyPrice(HouseDTO property);

    RoomResponseDTO getBiggestEnvironment(HouseDTO property);

    List<RoomResponseDTO> calculateSquareFeetPerEnvironment(HouseDTO property);
}
