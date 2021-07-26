package com.mercado_libre.bootcamp.desafio2.services.house;

import com.mercado_libre.bootcamp.desafio2.dtos.request.HouseRequestDTO;
import com.mercado_libre.bootcamp.desafio2.dtos.response.HouseResponseDTO;

public interface HouseCalculatorServiceI {
    HouseResponseDTO calculate(HouseRequestDTO houseRequest);
}
