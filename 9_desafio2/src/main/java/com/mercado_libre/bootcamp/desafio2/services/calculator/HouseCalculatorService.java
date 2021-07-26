package com.mercado_libre.bootcamp.desafio2.services.calculator;

import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;

public interface HouseCalculatorService {

    HouseResponseDTO calculate(HouseRequestDTO houseRequest);
}
