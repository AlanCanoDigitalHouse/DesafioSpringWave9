package com.mercadolibre.calculadorametroscuadrados.services;

import com.mercadolibre.calculadorametroscuadrados.dtos.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dtos.HouseResponseDTO;
import com.mercadolibre.calculadorametroscuadrados.exceptions.DistrictNotFoundException;
import com.mercadolibre.calculadorametroscuadrados.exceptions.IncorrectDistrictPriceException;

public interface CalculateService {
    HouseResponseDTO calculate(HouseDTO house) throws DistrictNotFoundException, IncorrectDistrictPriceException;
}
