package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.response.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exception.DataNotFound;

public interface ICalculateService {
    HouseResponseDTO calculateHome(HouseDTO house) throws DataNotFound;
}
