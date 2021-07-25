package com.mercadolibre.calculadorametroscuadrados.service;

import com.mercadolibre.calculadorametroscuadrados.dto.HouseDTO;
import com.mercadolibre.calculadorametroscuadrados.dto.HouseResponseDTO;

public interface ICalculateService {

    HouseResponseDTO calculate(HouseDTO house);

    void validatePropertyName(String prop_name);

    double calculatePrice(Double result,Double price);
}
