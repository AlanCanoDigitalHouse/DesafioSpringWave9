package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.Request.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.Response.HouseResponseDTO;

public interface ICalculateService {

    HouseResponseDTO calculateHouse(HouseRequestDTO house);

    Double totalValueHouse(HouseRequestDTO house);
}
