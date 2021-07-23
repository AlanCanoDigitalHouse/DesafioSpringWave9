package com.mercadolibre.desafiotesting.services;

import com.mercadolibre.desafiotesting.dto.HouseResponseDto;
import com.mercadolibre.desafiotesting.dto.HouseRequestDto;
import com.mercadolibre.desafiotesting.exceptions.DistrictException;

public interface HouseService {

    HouseResponseDto calculate(HouseRequestDto house) throws DistrictException;
}
