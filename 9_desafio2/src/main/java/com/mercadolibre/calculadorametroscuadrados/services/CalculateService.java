package com.mercadolibre.calculadorametroscuadrados.services;

import com.mercadolibre.calculadorametroscuadrados.dto.requests.HouseRequestDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectLocationPrice;
import com.mercadolibre.calculadorametroscuadrados.exceptions.LocationNotFound;

public interface CalculateService {
    HouseResponseDTO calculate(HouseRequestDTO house) throws LocationNotFound, IncorrectLocationPrice;
}
