package com.meli.joescaos.desafiotesting.services;

import com.meli.joescaos.desafiotesting.dto.HouseDTO;
import com.meli.joescaos.desafiotesting.dto.HouseResponseDTO;

public interface CalculateService {
    HouseResponseDTO calculate(HouseDTO house);
}
